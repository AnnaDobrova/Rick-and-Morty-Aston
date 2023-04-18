package com.example.rickandmorty.presentation.characters.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeDataToEpisodeMapper
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeListAdapter
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.episodes.EpisodeDetailsListener

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details){

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val episodeListAdapter: EpisodeListAdapter by lazy {
        EpisodeListAdapter(requireActivity() as EpisodeListDetailsListener)
    }

    private val viewModel by lazy {
        CharactersDetailViewModel()
    }

    private val mapperEpisode: EpisodeDataToEpisodeMapper by lazy {
        EpisodeDataToEpisodeMapper()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val characterId = requireArguments().getInt(CHARACTER_ID)
        viewModel.loadCharacterById(characterId)
        observeVM()
        initRecycler()
    }

    private fun observeVM() {
        viewModel.getCharacter().observe(viewLifecycleOwner) { characterDetail ->
            fillCharacterDetail(characterDetail)
        }
    }

    private fun fillCharacterDetail(characterDetail: CharacterDetailUi) {
        with(binding) {
            nameCharacterDetails.text = characterDetail.name
            speciesCharacterDetails.text = characterDetail.species
            genderCharacterDetails.text = characterDetail.gender
            statusCharacterDetails.text = characterDetail.status
        }
        Glide.with(this@CharacterDetailsFragment)
            .load(characterDetail.image)
            .into(binding.imageCharacterDetails)
//        characterDetail.episodeList.map { episode ->
//            episode.last().digitToIntOrNull()?.let { episodeDetailsRepository.loadEpisodeById(it) }
//        }
    }

//    override fun getEpisodeById(episode:SingleEpisodeDetailData) {
//        episodeListAdapter.addEpisode(mapperEpisode.map(episode))
//    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in CharacterDetailsFragment ")
        with(binding.recyclerViewDetails) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = episodeListAdapter
        }
    }

    companion object {
        const val TAG = "CharacterDetailsFragment"
        private const val CHARACTER_ID = "ID"

        fun newInstance(singleCharacterUi: SingleCharacterUi) = CharacterDetailsFragment().also {
            it.arguments = bundleOf(
                CHARACTER_ID to singleCharacterUi.id,
            )
        }
    }
}