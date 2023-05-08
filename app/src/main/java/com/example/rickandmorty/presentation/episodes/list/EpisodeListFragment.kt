package com.example.rickandmorty.presentation.episodes.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
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
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.episodes.list.adapter.EpisodeAdapter
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import com.example.rickandmorty.utils.ViewModelFactory
import java.util.Locale
import javax.inject.Inject

class EpisodeListFragment : Fragment(R.layout.fragment_episodes) {

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val episodeAdapter: EpisodeAdapter by lazy {
        EpisodeAdapter(requireActivity() as EpisodeListDetailsListener)
    }

    var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject lateinit var viewModel: EpisodesViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rickAndMortyComponent = (activity as MainActivity).rickAndMortyComponent
        rickAndMortyComponent?.inject(this)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = rickAndMortyComponent!!.getViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[EpisodesViewModel::class.java]

        binding.episodesPb.showProgress()
        isConnect()
        observeVM()
        initRecycler()
        updateNetwork()
        searchEpisodes()
    }

    private fun observeVM() {
        viewModel.getAllEpisodes().observe(viewLifecycleOwner) { newCharacterList ->
            episodeAdapter.updateEpisodes(newCharacterList)
            binding.episodesPb.hideProgress()
        }
        viewModel.getError().observe(viewLifecycleOwner) { error ->
            binding.episodesPb.hideProgress()
            Toast.makeText(requireContext(), R.string.error_connectivity, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler() {
        with(binding.recyclerViewEpisode) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = episodeAdapter
        }
    }

    private fun updateNetwork() {
        with(binding.swipeEpisodes) {
            setOnRefreshListener {
                viewModel.loadAllEpisodes()
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
            val list = mutableListOf<SingleEpisodeUI>()
            val filterList = mutableListOf<SingleEpisodeUI>()
            viewModel.getAllEpisodes().observe(viewLifecycleOwner) { newCharacterList ->
                list.addAll(newCharacterList)
            }
            for (i in list) {
                if (i.nameEpisode.lowercase(Locale.ROOT).contains(query)) {
                    filterList.add(i)
                }
            }
            if (filterList.isNotEmpty()) {
                episodeAdapter.updateEpisodes(filterList)
            }
        }
    }

    companion object {
        const val TAG_EPISODES_FRAGMENT = "TAG_EPISODES_FRAGMENT"

        fun newInstance() = EpisodeListFragment()
    }

}