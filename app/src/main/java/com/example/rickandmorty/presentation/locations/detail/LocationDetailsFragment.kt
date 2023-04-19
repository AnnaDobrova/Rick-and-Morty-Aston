package com.example.rickandmorty.presentation.locations.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.locations.detail.LocationDetailsRepositoryImpl
import com.example.rickandmorty.databinding.FragmentLocetionDetailsBinding
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI

class LocationDetailsFragment : Fragment(R.layout.fragment_locetion_details) {

    private var _binding: FragmentLocetionDetailsBinding? = null
    private val binding get() = _binding!!

    private val mapper: LocationDetailsDomainToLocationDetailsUIMapper by lazy {
        LocationDetailsDomainToLocationDetailsUIMapper()
    }

    private val viewModel by lazy {
        LocationDetailViewModel()
    }

    private val locationDetailsRepositoryImpl: LocationDetailsRepositoryImpl by lazy {
        LocationDetailsRepositoryImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLocetionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val locationId = requireArguments().getLong(LOCATION_ID)
        viewModel.loadLocationById(locationId.toInt())
        observeVm()
    }

    private fun observeVm() {
        viewModel.getLocation().observe(viewLifecycleOwner) { locationDetail ->
            fillLocationDetail(locationDetail)

        }
    }

    private fun fillLocationDetail(locationDetail: LocationDetailUi) {
        with(binding) {
            nameLocationDetails.text = locationDetail.name
            typeLocationDetails.text = locationDetail.type
            dimensionLocationDetails.text = locationDetail.dimension
        }
    }

    companion object {
        const val TAG = "LocationDetailsFragment"
        private const val LOCATION_ID = "ID"

        fun newInstance(singleLocationUi: SingleLocationUI) = LocationDetailsFragment().also {
            it.arguments = bundleOf(
                LOCATION_ID to singleLocationUi.id
            )
        }
    }
}