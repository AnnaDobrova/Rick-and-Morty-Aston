package com.example.rickandmorty.presentation.locations.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LocationBinding
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI

class LocationViewHolder(
    itemView: View,
    private val locationListDetailsListener: LocationListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: LocationBinding = LocationBinding.bind(itemView)

    fun bindLocation(
        location: SingleLocationUI
    ) {
        with(binding) {
            idLocation.text = location.id.toString()
            nameLocation.text = location.name
            typeLocation.text = location.type
            dimensionLocation.text = location.dimension
        }
        itemView.setOnClickListener {
            locationListDetailsListener.goToDetailsLocation(location)
        }
    }

}