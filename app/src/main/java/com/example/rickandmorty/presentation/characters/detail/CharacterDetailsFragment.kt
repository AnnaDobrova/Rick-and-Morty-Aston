package com.example.rickandmorty.presentation.characters.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeDataToEpisodeMapper
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeListAdapter
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.utils.ViewModelFactory
import javax.inject.Inject

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val episodeListAdapter: EpisodeListAdapter by lazy {
        EpisodeListAdapter(requireActivity() as EpisodeListDetailsListener)
    }

    var rickAndMortyComponent: RickAndMortyComponent? = null

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersDetailViewModel::class.java]
        val characterId = requireArguments().getInt(CHARACTER_ID)
        viewModel.getCharacterById(characterId)
        observeVM()
        initRecycler()
        visibilityToolBar()
    }

    private fun observeVM() {
        viewModel.getCharacter().observe(viewLifecycleOwner) { characterDetail ->
            fillCharacterDetail(characterDetail)
        }
        viewModel.getEpisodeList().observe(viewLifecycleOwner) { episodeList ->
            binding.progressBar.hideProgress()
            episodeListAdapter.updateEpisodeList(episodeList)
        }
    }

    private fun fillCharacterDetail(characterDetail: CharacterDetailUi) {
        with(binding) {
            nameCharacterDetails.text = characterDetail.name
            speciesCharacterDetails.text = characterDetail.species
            genderCharacterDetails.text = characterDetail.gender
            statusCharacterDetails.text = characterDetail.status

            Glide.with(this@CharacterDetailsFragment)
                .load(characterDetail.image)
                .into(imageCharacterDetails)

            viewModel.loadEpisodeById(characterDetail.episodeList)
            progressBar.showProgress()
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