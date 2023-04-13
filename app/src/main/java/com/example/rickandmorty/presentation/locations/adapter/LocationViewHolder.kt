package com.example.rickandmorty.presentation.locations.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LocationBinding
import com.example.rickandmorty.domain.location.Location

class LocationViewHolder(
    location: View
): RecyclerView.ViewHolder(location) {

    private val binding: LocationBinding = LocationBinding.bind(location)

    fun bindLocation(
        location: Location
    ){
       with(binding){
           idLocation.text = location.id.toString()
           nameLocation.text = location.nameLocation
           typeLocation.text = location.typeLocation
           dimensionLocation.text = location.dimensionLocation
       }
    }

}