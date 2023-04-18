package com.example.rickandmorty.presentation.locations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.locations.LocationsRepository
import com.example.rickandmorty.data.locations.list.model.SingleLocationData
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.presentation.locations.adapter.LocationAdapter
import com.example.rickandmorty.presentation.locations.mapper.LocationDataToLocationMapper

class LocationsFragment : Fragment(R.layout.fragment_locations), LocationsListener {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val locationAdapter: LocationAdapter by lazy {
        LocationAdapter(requireActivity() as LocationListDetailsListener)
    }

    private val locationRepository: LocationsRepository by lazy {
        LocationsRepository()
    }

    private val mapper: LocationDataToLocationMapper by lazy {
        LocationDataToLocationMapper()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllLocations()
        initRecycler()
    }

    override fun getAllLocations(locations: List<SingleLocationData>) {
        locationAdapter.updateLocations(mapper.map(locations))
    }

    private fun initAllLocations() {
        with(locationRepository) {
            registerListener(this@LocationsFragment)
            loadCharacters()
        }
    }

    private fun initRecycler() {
        with(binding.recyclerViewLocation) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = locationAdapter
        }
    }

    companion object {
        const val TAG_LOCATIONS_FRAGMENT = "TAG_LOCATIONS_FRAGMENT"

        fun newInstance() = LocationsFragment()
    }
}