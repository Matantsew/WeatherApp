package com.example.weatherapp.fragments.weatherTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.adapters.WeekForecastAdapter
import com.example.weatherapp.databinding.FragmentWeekTabBinding
import com.example.weatherapp.viewModels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeekTabFragment : Fragment() {

    private var _binding: FragmentWeekTabBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModel()

    private lateinit var weekForecastAdapter : WeekForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekForecastAdapter = WeekForecastAdapter(listOf())

        binding.weekForecastRv.adapter = weekForecastAdapter

        lifecycleScope.launch {
            viewModel.weatherCurrentFlow.collectLatest { weatherCurrentFlow ->
                val weather = weatherCurrentFlow?.data?.last()
                weather?.let {
                    with(binding) {
                        val temperature = "${it.temp.toInt()} ℃"
                        currentTemperatureTv.text = temperature

                        currentCityTv.text = it.city_name
                        currentTimeTv.text = it.ob_time
                        currentDescriptionTv.text = it.weather.description
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.weatherForecastFlow.collectLatest { weatherForecastFlow ->
                val data = weatherForecastFlow?.data

                data?.let {
                    weekForecastAdapter.updateItems(it)
                }
            }
        }
    }
}