package com.example.weatherapp.fragments.weatherTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentWeekTabBinding

class WeekTabFragment : Fragment() {

    private var _binding: FragmentWeekTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekTabBinding.inflate(layoutInflater)
        return binding.root
    }


}