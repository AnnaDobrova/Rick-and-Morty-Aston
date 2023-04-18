package com.example.rickandmorty.presentation.locations.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.locations.LocationDetailsRepository
import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.databinding.FragmentLocetionDetailsBinding
import com.example.rickandmorty.domain.location.list.Location
import com.example.rickandmorty.presentation.locations.LocationDetailsListener
import com.example.rickandmorty.presentation.locations.mapper.LocationDetailsDataToLocationDetailsMap

class LocationDetailsFragment : Fragment(R.layout.fragment_locetion_details), LocationDetailsListener {

    private var _binding: FragmentLocetionDetailsBinding? = null
    private val binding get() = _binding!!

    private val mapper: LocationDetailsDataToLocationDetailsMap by lazy {
        LocationDetailsDataToLocationDetailsMap()
    }

    private val locationDetailsRepository: LocationDetailsRepository by lazy {
        LocationDetailsRepository()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLocetionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initLocationDetails()
    }

    override fun getLocationById(location: LocationDetailData) {
        val locationDetails = mapper.map(location)
        with(binding){
            nameLocationDetails.text = locationDetails.nameLocation
            typeLocationDetails.text = locationDetails.typeLocation
            dimensionLocationDetails.text = locationDetails.dimensionLocation
        }
    }
    private fun initLocationDetails(){
        val id = requireArguments().getInt(LOCATION_ID)
        with(locationDetailsRepository){
            registerListener(this@LocationDetailsFragment)
            loadLocationById(id)
        }
    }

    companion object {
        const val TAG = "LocationDetailsFragment"
        private const val LOCATION_ID = "ID"

        fun newInstance(location: Location) = LocationDetailsFragment().also {
            it.arguments = bundleOf(
                LOCATION_ID to location.id
            )
        }
    }
}