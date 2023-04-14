package com.example.rickandmorty.presentation.episodes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.episodes.EpisodeDetailsRepository
import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.domain.episode.list.Episode
import com.example.rickandmorty.presentation.episodes.EpisodeDetailsListener
import com.example.rickandmorty.presentation.episodes.mapper.EpisodeDetailsDataToEpisodeDetailsMap

class EpisodeDetailsFragment : Fragment(R.layout.fragment_episode_details), EpisodeDetailsListener {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!

    private val mapper: EpisodeDetailsDataToEpisodeDetailsMap by lazy {
        EpisodeDetailsDataToEpisodeDetailsMap()
    }

    private val episodeDetailsRepository: EpisodeDetailsRepository by lazy {
        EpisodeDetailsRepository()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initEpisodeDetails()
    }

    fun initEpisodeDetails() {
        val id = requireArguments().getInt(EPISODE_ID)
        with(episodeDetailsRepository) {
            registerListener(this@EpisodeDetailsFragment)
            loadEpisodeById(id)
        }
    }

    override fun getEpisodeById(episode: EpisodesData.SingleEpisodeData) {
        val episodeDetails = mapper.map(episode)
        with(binding) {
            nameEpisodeDetails.text = episodeDetails.nameEpisode
            numberEpisodeDetails.text = episodeDetails.numberEpisode
            airDataEpisodeDetails.text = episodeDetails.airDataEpisode
        }
    }

    companion object {
        const val TAG = "EpisodeDetailsFragment"
        private const val EPISODE_ID = "ID"

        fun newInstance(episode: Episode) = EpisodeDetailsFragment().also {
            it.arguments = bundleOf(
                EPISODE_ID to episode.id
            )
        }
    }
}