package com.example.rickandmorty.presentation.episodes.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.episodes.detail.EpisodeDetailsRepositoryImpl
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import com.example.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.presentation.episodes.EpisodeDetailsListener
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.presentation.episodes.list.EpisodesViewModel
import com.example.rickandmorty.presentation.episodes.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

class EpisodeDetailsFragment : Fragment(R.layout.fragment_episode_details) {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val viewModel by lazy {
        EpisodeDetailViewModel()
    }

    private val episodeDetailsRepositoryImpl: EpisodeDetailsRepositoryImpl by lazy {
        EpisodeDetailsRepositoryImpl()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val episodeId = requireArguments().getInt(EPISODE_ID)
        viewModel.loadEpisodeById(episodeId)
        observeVM()
    }

    private fun observeVM() {
        viewModel.getEpisode().observe(viewLifecycleOwner) { episodeDetail ->
            fillEpisodeDetail(episodeDetail)
        }
    }

    private fun fillEpisodeDetail(episodeDetail: EpisodeDetailUi) {
        with(binding) {
            nameEpisodeDetails.text = episodeDetail.nameEpisode
            numberEpisodeDetails.text = episodeDetail.numberEpisode
            airDataEpisodeDetails.text = episodeDetail.airDataEpisode
        }
    }

    companion object {
        const val TAG = "EpisodeDetailsFragment"
        private const val EPISODE_ID = "ID"

        fun newInstance(episode: SingleEpisodeUI) = EpisodeDetailsFragment().also {
            it.arguments = bundleOf(
                EPISODE_ID to episode.id
            )
        }
    }
}