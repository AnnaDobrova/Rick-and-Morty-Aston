package com.example.rickandmorty.presentation.locations.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.presentation.locations.list.adapter.LocationAdapter
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI
import com.example.rickandmorty.utils.ViewModelFactory
import java.util.Locale
import javax.inject.Inject

class LocationListFragment : Fragment(R.layout.fragment_locations) {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val locationAdapter: LocationAdapter by lazy {
        LocationAdapter(requireActivity() as LocationListDetailsListener)
    }

    var rickAnMortyComponent: RickAndMortyComponent? = null

    @Inject lateinit var viewModel: LocationsViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rickAnMortyComponent = (activity as MainActivity).rickAndMortyComponent
        rickAnMortyComponent?.inject(this)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = rickAnMortyComponent!!.getViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[LocationsViewModel::class.java]

        binding.locationsPb.showProgress()
        observerVM()
        initRecycler()
        updateNetwork()
        searchEpisodes()
    }

    private fun observerVM() {
        viewModel.getLocations().observe(viewLifecycleOwner) { newLocationList ->
            locationAdapter.updateLocations(newLocationList)
            binding.locationsPb.hideProgress()
        }
        viewModel.getError().observe(viewLifecycleOwner) { error ->
            binding.locationsPb.hideProgress()
            Toast.makeText(requireContext(), R.string.error_connectivity, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in LocationListFragment")
        with(binding.recyclerViewLocation) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = locationAdapter
        }
        binding.locationsPb.hideProgress()
    }

    private fun updateNetwork() {
        with(binding.swipeLocations) {
            setOnRefreshListener {
                viewModel.loadAllLocations()
                this.isRefreshing = false
            }
        }
    }

    private fun searchEpisodes() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val list = mutableListOf<SingleLocationUI>()
            val filterList = mutableListOf<SingleLocationUI>()
            viewModel.getLocations().observe(viewLifecycleOwner) { newCharacterList ->
                list.addAll(newCharacterList)
            }
            for (i in list) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filterList.add(i)
                }
            }
            if (filterList.isNotEmpty()) {
                locationAdapter.updateLocations(filterList)
            }
        }
    }

    companion object {
        const val TAG_LOCATIONS_FRAGMENT = "TAG_LOCATIONS_FRAGMENT"

        fun newInstance() = LocationListFragment()
    }
}