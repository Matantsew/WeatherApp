package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherTabsAdapter
import com.example.weatherapp.databinding.FragmentWeatherPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class WeatherPagerFragment : Fragment() {

    private var _binding: FragmentWeatherPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        pager.adapter = WeatherTabsAdapter(this@WeatherPagerFragment)

        TabLayoutMediator(tabs, pager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.tab_week_weather)
                }
                1 -> {
                    tab.text = getString(R.string.tab_today_weather)
                }
            }
        }.attach()
    }
}