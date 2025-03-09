package com.example.weatherapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeekdayWeatherBinding
import com.example.weatherapp.models.WeatherForecastData

class WeekForecastAdapter(private var data: List<WeatherForecastData>) : RecyclerView.Adapter<WeekdayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekdayViewHolder {
        val binding = ItemWeekdayWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return WeekdayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(data.size >= 7) 7 else data.size
    }

    override fun onBindViewHolder(holder: WeekdayViewHolder, position: Int) {
        holder.bind(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(data: List<WeatherForecastData>) {
        this.data = data
        notifyDataSetChanged()
    }
}

class WeekdayViewHolder(private val binding: ItemWeekdayWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherForecastData: WeatherForecastData) {
        val text = "${weatherForecastData.temp.toInt()} ℃"
        binding.temperatureTv.text = text
        binding.dateTv.text = weatherForecastData.datetime
        binding.descriptionTv.text = weatherForecastData.weather.description
    }
}