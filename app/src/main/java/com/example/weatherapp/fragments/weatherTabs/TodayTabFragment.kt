package com.example.weatherapp.fragments.weatherTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.databinding.FragmentTodayTabBinding
import com.example.weatherapp.viewModels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TodayTabFragment : Fragment() {

    private var _binding: FragmentTodayTabBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.weatherCurrentFlow.collectLatest { weatherCurrentFlow ->
                val data = weatherCurrentFlow?.data?.last()

                data?.let {
                    with(binding) {
                        cityNameTv.text = it.city_name
                        timeDataTv.text = it.ob_time
                        sunriseDataTv.text = it.sunrise
                        sunsetDataTv.text = it.sunset
                    }
                }
            }
        }

    }
}