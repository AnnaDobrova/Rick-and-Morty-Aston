package com.example.rickandmorty.presentation.characters.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.characters.detail.CharacterDetailsRepositoryImpl
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.data.episodes.EpisodeDetailsRepository
import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeDataToEpisodeMapper
import com.example.rickandmorty.presentation.characters.detail.adapter.EpisodeListAdapter
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.episodes.EpisodeDetailsListener

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details),
    EpisodeDetailsListener {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private val episodeListAdapter: EpisodeListAdapter by lazy {
        EpisodeListAdapter(requireActivity() as EpisodeListDetailsListener)
    }

    private val characterDetailsRepository: CharacterDetailsRepositoryImpl by lazy {
        CharacterDetailsRepositoryImpl()
    }

    private val episodeDetailsRepository: EpisodeDetailsRepository by lazy {
        EpisodeDetailsRepository()
    }

    private val mapperCharacter: CharacterDetailDomainToCharacterDetailUiMapper by lazy {
        CharacterDetailDomainToCharacterDetailUiMapper()
    }

    private val mapperEpisode: EpisodeDataToEpisodeMapper by lazy {
        EpisodeDataToEpisodeMapper()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCharacterDetail()
        initRecycler()
    }

    override fun getCharacterById(character: SingleCharacterData) {
        val characterDetail = mapperCharacter.map(character)
        with(binding) {
            nameCharacterDetails.text = characterDetail.name
            speciesCharacterDetails.text = characterDetail.species
            genderCharacterDetails.text = characterDetail.gender
            statusCharacterDetails.text = characterDetail.status
        }
        Glide.with(this@CharacterDetailsFragment)
            .load(characterDetail.image)
            .into(binding.imageCharacterDetails)
        Log.w("TAG", "getCharacterById: 1", )
        initEpisodeDetail()
        characterDetail.episodeList.map { episode ->
            Log.w("TAG", "getCharacterById: 2", )

            episode.last().digitToIntOrNull()?.let { episodeDetailsRepository.loadEpisodeById(it) }
        }
    }

    override fun getEpisodeById(episode: EpisodesData.SingleEpisodeData) {
        episodeListAdapter.addEpisode(mapperEpisode.map(episode))
    }

    private fun initCharacterDetail() {
        val id = requireArguments().getInt(CHARACTER_ID)
        with(characterDetailsRepository) {
            registerListener(this@CharacterDetailsFragment)
            loadCharacterById(id)
        }
    }

    private fun initEpisodeDetail() {
        episodeDetailsRepository.registerListener(this@CharacterDetailsFragment)
    }

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