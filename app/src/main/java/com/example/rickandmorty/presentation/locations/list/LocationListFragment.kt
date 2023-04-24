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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.locations.LocationsRepositoryImpl
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.presentation.locations.list.adapter.LocationAdapter
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper

class LocationListFragment : Fragment(R.layout.fragment_locations) {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val locationAdapter: LocationAdapter by lazy {
        LocationAdapter(requireActivity() as LocationListDetailsListener)
    }

    private val viewModel: LocationsViewModel by lazy {
        LocationsViewModel()
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
        binding.locationsPb.showProgress()
        isConnect()
        observerVM()
        initRecycler()
        updateNetwork()
    }

    private fun observerVM() {
        viewModel.getLocations().observe(viewLifecycleOwner) { newLocationList ->
            locationAdapter.updateLocations(newLocationList)
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

    private fun isConnect(): Boolean {
        val connectivityManager = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        connectivityManager.apply {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                } ?: false
            } else {
                activeNetworkInfo?.isConnected ?: false
            }
        }
    }

    companion object {
        const val TAG_LOCATIONS_FRAGMENT = "TAG_LOCATIONS_FRAGMENT"

        fun newInstance() = LocationListFragment()
    }
}