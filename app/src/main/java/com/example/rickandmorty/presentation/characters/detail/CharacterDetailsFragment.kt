package com.example.rickandmorty.presentation.characters.detail

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeListAdapter
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.utils.ViewModelFactory
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val episodeListAdapter: EpisodeListAdapter by lazy {
        EpisodeListAdapter(requireActivity() as EpisodeListDetailsListener)
    }

    private var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject
    lateinit var viewModel: CharactersDetailViewModel

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
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersDetailViewModel::class.java]
        val characterId = requireArguments().getInt(CHARACTER_ID)
        viewModel.loadCharacterDetail(characterId)
        observeVM()
        initRecycler()
        visibilityToolBar()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCharacter().collect { viewState ->
                    updateCharactersDetailsState(viewState)
                }
            }
        }

    }

    private fun updateCharactersDetailsState(state: ViewState<CharacterDetailUi>) {
        when (state) {
            is ViewState.Loading -> updateLoadingState()
            is ViewState.Error -> updateErrorState(state.error.message.orEmpty())
            is ViewState.Data -> updateDataState(state.data)
        }
    }

    private fun updateDataState(character: CharacterDetailUi) {
        binding.progressBar.hideProgress()
        fillCharacterDetail(character)
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

    private fun fillCharacterDetail(characterDetail: CharacterDetailUi) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding) {
                    nameCharacterDetails.text = characterDetail.name
                    speciesCharacterDetails.text = characterDetail.species
                    genderCharacterDetails.text = characterDetail.gender
                    statusCharacterDetails.text = characterDetail.status

                    Glide.with(this@CharacterDetailsFragment)
                        .load(characterDetail.image)
                        .into(imageCharacterDetails)

                }
                viewModel.loadEpisodeById(characterDetail.episodeList)
                binding.progressBar.showProgress()
                viewModel.getEpisodeList().collect { viewState1 ->
                    when (viewState1) {
                        is ViewState.Loading -> binding.progressBar.showProgress()
                        is ViewState.Error -> {
                            binding.progressBar.hideProgress()
                            Toast.makeText(
                                requireActivity(),
                                viewState1.error.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is ViewState.Data -> {
                            binding.progressBar.hideProgress()
                            episodeListAdapter.updateEpisodeList(viewState1.data)
                        }
                    }
                }
            }
        }
    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in CharacterDetailsFragment ")
        with(binding.recyclerViewDetails) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = episodeListAdapter
        }
    }

    private fun visibilityToolBar() {
        binding.toolbarCharacterDetail.setOnClickListener {
            callBackForFragments?.back()
            binding.toolbarCharacterDetail.visibility = View.GONE
        }
    }

    companion object {
        const val TAG = "CharacterDetailsFragment"
        private const val CHARACTER_ID = "ID"

        fun newInstance(id: Int) = CharacterDetailsFragment().also {
            it.arguments = bundleOf(
                CHARACTER_ID to id,
            )
        }
    }
}