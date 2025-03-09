package com.example.weatherapp

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.fragments.WeatherPagerFragment
import com.example.weatherapp.services.LocationService
import com.example.weatherapp.viewModels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ServiceConnection {

    private val mainViewModel: MainViewModel by viewModel()

    private var locationService: LocationService? = null
    private var isBound = false

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            bindLocationService()
        } else {
            Log.e("MainActivity", "Location permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, WeatherPagerFragment())
            .commit()

        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun bindLocationService() {
        Intent(this, LocationService::class.java).also { intent ->
            bindService(intent, this, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

        val binder = service as LocationService.LocalBinder
        locationService = binder.getService()
        isBound = true

        // Collect GPS location updates
        lifecycleScope.launch {
            locationService?.locationFlow?.collectLatest { location ->
                location?.let {
                    Log.d("MainActivity", "Lat: ${it.latitude}, Lng: ${it.longitude}")
                    mainViewModel.obtainWeather(it)
                }
            }
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isBound = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(this)
            isBound = false
        }
    }
}