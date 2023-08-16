package com.example.rickandmorty.presentation.episodes.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.episodes.details.adapter.CharacterListInEpisodeAdapter
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.utils.ViewModelFactory
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsFragment : Fragment(R.layout.fragment_episode_details) {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val characterListInEpisodeAdapter: CharacterListInEpisodeAdapter by lazy {
        CharacterListInEpisodeAdapter(requireActivity() as CharacterListDetailsListener)
    }
    private var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject
    lateinit var viewModel: EpisodeDetailViewModel

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
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val episodeId = requireArguments().getInt(EPISODE_ID)
        viewModel = ViewModelProvider(this, viewModelFactory)[EpisodeDetailViewModel::class.java]
        viewModel.loadEpisodeDetail(episodeId)
        observeVM()
        initRecycler()
        visibilityToolBar()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getEpisode().collect { viewState ->
                    updateCharactersDetailsState(viewState)
                }
            }
        }
    }

    private fun updateCharactersDetailsState(state: ViewState<EpisodeDetailUi>) {
        when (state) {
            is ViewState.Loading -> updateLoadingState()
            is ViewState.Error -> updateErrorState(state.error.message.orEmpty())
            is ViewState.Data -> updateDataState(state.data)
        }
    }

    private fun updateDataState(data: EpisodeDetailUi) {
        binding.progressBar.hideProgress()
        fillEpisodeDetail(data)
    }

    private fun updateErrorState(message: String) {
        binding.progressBar.hideProgress()
        Toast.makeText(
            requireActivity(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateLoadingState() {
        binding.progressBar.showProgress()
    }

    private fun fillEpisodeDetail(episodeDetail: EpisodeDetailUi) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding) {
                    nameEpisodeDetails.text = episodeDetail.nameEpisode
                    numberEpisodeDetails.text = episodeDetail.numberEpisode
                    airDataEpisodeDetails.text = episodeDetail.airDataEpisode

                    viewModel.loadCharacterById(episodeDetail.characters)
                    progressBar.showProgress()
                }
                viewModel.getCharacterList().collect { viewState ->
                    when (viewState) {
                        is ViewState.Loading -> updateLoadingState()
                        is ViewState.Error -> updateErrorState(viewState.error.message.orEmpty())
                        is ViewState.Data -> {
                            characterListInEpisodeAdapter.updateCharacterList(viewState.data)
                            binding.progressBar.hideProgress()
                        }
                    }
                }

            }
        }
    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in EpisodeDetailsFragment ")
        with(binding.recyclerViewEpisodes) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = characterListInEpisodeAdapter
        }
    }

    private fun visibilityToolBar() {
        binding.toolbarEpisodeDetail.setOnClickListener {
            callBackForFragments?.back()
            binding.toolbarEpisodeDetail.visibility = View.GONE
        }
    }

    companion object {
        const val TAG = "EpisodeDetailsFragment"
        private const val EPISODE_ID = "ID"

        fun newInstance(id: Int) = EpisodeDetailsFragment().also {
            it.arguments = bundleOf(
                EPISODE_ID to id
            )
        }
    }
}