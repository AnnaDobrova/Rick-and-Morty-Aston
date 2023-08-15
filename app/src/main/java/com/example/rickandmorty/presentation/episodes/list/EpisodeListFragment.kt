package com.example.rickandmorty.presentation.episodes.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.episodes.list.adapter.EpisodeAdapter
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import com.example.rickandmorty.utils.Connectivity
import com.example.rickandmorty.utils.ViewModelFactory
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


class EpisodeListFragment : Fragment(R.layout.fragment_episodes) {

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val episodeAdapter: EpisodeAdapter by lazy {
        EpisodeAdapter(requireActivity() as EpisodeListDetailsListener)
    }

    private var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject
    lateinit var viewModel: EpisodesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[EpisodesViewModel::class.java]

        binding.episodesPb.showProgress()
        observeVM()
        initRecycler()
        updateNetwork()
        searchEpisodes()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllEpisodes().collect { viewState ->
                    updateEpisodeState(viewState)
                }
            }
        }
    }

    private fun updateEpisodeState(state: ViewState<List<SingleEpisodeUI>>) {
        when (state) {
            is ViewState.Loading -> updateLoadingState()
            is ViewState.Error -> updateErrorState(state.error.message.orEmpty())
            is ViewState.Data -> updateDataState(state.data)
        }
    }

    private fun updateDataState(list: List<SingleEpisodeUI>) {
        episodeAdapter.updateEpisodes(list)
        binding.episodesPb.hideProgress()
    }

    private fun updateErrorState(message: String) {
        binding.episodesPb.hideProgress()
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateLoadingState() {
        binding.episodesPb.showProgress()
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
                viewModel.load()
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
            val list = mutableListOf<SingleEpisodeUI>()
            val filterList = mutableListOf<SingleEpisodeUI>()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getAllEpisodes().collect { newCharacterList ->
                        when (newCharacterList) {
                            is ViewState.Loading -> updateLoadingState()
                            is ViewState.Data -> list.addAll(newCharacterList.data)
                            is ViewState.Error -> newCharacterList.error
                        }
                    }
                }
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