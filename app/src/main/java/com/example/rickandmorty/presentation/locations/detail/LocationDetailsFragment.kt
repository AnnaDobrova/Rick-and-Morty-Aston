package com.example.rickandmorty.presentation.locations.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.data.locations.detail.LocationDetailsRepositoryImpl
import com.example.rickandmorty.databinding.FragmentLocetionDetailsBinding
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.episodes.details.adapter.CharacterListInEpisodeAdapter
import com.example.rickandmorty.presentation.locations.detail.adapter.CharacterListInLocationAdapter
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi

class LocationDetailsFragment : Fragment(R.layout.fragment_locetion_details) {

    private var _binding: FragmentLocetionDetailsBinding? = null
    private val binding get() = _binding!!

    private val characterListInLocationAdapter: CharacterListInLocationAdapter by lazy {
        CharacterListInLocationAdapter(requireActivity() as CharacterListDetailsListener)
    }

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
        val locationId = requireArguments().getInt(LOCATION_ID)
        viewModel.loadLocationById(locationId)
        observeVm()
        initRecycler()
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
        characterListInLocationAdapter.updateCharacterListString(locationDetail.listCharactersInLocation)
    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in EpisodeDetailsFragment ")
        with(binding.recyclerViewLocations) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = characterListInLocationAdapter
        }
    }

    companion object {
        const val TAG = "LocationDetailsFragment"
        private const val LOCATION_ID = "ID"

        fun newInstance(id: Int) = LocationDetailsFragment().also {
            it.arguments = bundleOf(
                LOCATION_ID to id
            )
        }
    }
}