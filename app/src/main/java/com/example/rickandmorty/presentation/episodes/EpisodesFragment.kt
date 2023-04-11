package com.example.rickandmorty.presentation.episodes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.episodes.EpisodesRepository
import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.presentation.episodes.adapter.EpisodeAdapter
import com.example.rickandmorty.presentation.episodes.mapper.EpisodeDataToEpisodeMap

class EpisodesFragment : Fragment(R.layout.fragment_episodes), EpisodesListener {

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val episodeAdapter: EpisodeAdapter by lazy {
        EpisodeAdapter()
    }

    private val mapper: EpisodeDataToEpisodeMap by lazy {
        EpisodeDataToEpisodeMap()
    }
    private val episodeRepository: EpisodesRepository by lazy {
        EpisodesRepository()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllEpisodes()
        initRecycler()

    }

    override fun getAllEpisodes(episode: List<EpisodesData.SingleEpisodeData>) {
        episodeAdapter.updateEpisodes(mapper.map(episode))
    }

    fun initAllEpisodes() {
        with(episodeRepository) {
            registerEpisode(this@EpisodesFragment)
            loadEpisodes()
        }
    }

    private fun initRecycler() {
        with(binding.recyclerViewEpisode) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = episodeAdapter
        }
    }

    companion object {
        const val TAG_EPISODES_FRAGMENT = "TAG_EPISODES_FRAGMENT"

        fun newInstance() = EpisodesFragment()
    }

}