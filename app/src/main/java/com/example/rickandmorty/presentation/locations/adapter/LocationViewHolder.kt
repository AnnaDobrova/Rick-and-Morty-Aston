package com.example.rickandmorty.presentation.locations.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LocationBinding
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.domain.location.list.Location

class LocationViewHolder(
    itemView: View,
    private val locationListDetailsListener: LocationListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: LocationBinding = LocationBinding.bind(itemView)

    fun bindLocation(
        location: Location
    ) {
        with(binding) {
            idLocation.text = location.id.toString()
            nameLocation.text = location.nameLocation
            typeLocation.text = location.typeLocation
            dimensionLocation.text = location.dimensionLocation
        }
        itemView.setOnClickListener {
            locationListDetailsListener.goToDetailsLocation(location)
        }
    }

}