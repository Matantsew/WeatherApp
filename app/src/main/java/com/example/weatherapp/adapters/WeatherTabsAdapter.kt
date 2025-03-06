package com.example.weatherapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.fragments.weatherTabs.TodayTabFragment
import com.example.weatherapp.fragments.weatherTabs.WeekTabFragment

class WeatherTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position) {
            1 -> TodayTabFragment()
            else -> WeekTabFragment()
        }
        return fragment
    }
}