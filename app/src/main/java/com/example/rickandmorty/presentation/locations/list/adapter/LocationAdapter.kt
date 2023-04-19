package com.example.rickandmorty.presentation.locations.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI

class LocationAdapter(private val locationListDetailsListener: LocationListDetailsListener) :
    RecyclerView.Adapter<LocationViewHolder>() {

    private var locations = mutableListOf<SingleLocationUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.location,
                parent,
                false
            ),
            locationListDetailsListener
        )

    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.bindLocation(location)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    fun updateLocations(newLocations: List<SingleLocationUI>) {
        locations.clear()
        locations.addAll(newLocations)
        notifyDataSetChanged()
    }
}