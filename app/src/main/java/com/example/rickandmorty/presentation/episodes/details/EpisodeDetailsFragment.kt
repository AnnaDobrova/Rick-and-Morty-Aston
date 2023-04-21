package com.example.rickandmorty.presentation.episodes.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.episodes.detail.EpisodeDetailsRepositoryImpl
import com.example.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.episodes.details.adapter.CharacterListInEpisodeAdapter
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi

class EpisodeDetailsFragment : Fragment(R.layout.fragment_episode_details) {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val characterListInEpisodeAdapter: CharacterListInEpisodeAdapter by lazy {
        CharacterListInEpisodeAdapter(requireActivity() as CharacterListDetailsListener)
    }

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
        initRecycler()
        visibilityToolBar()
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
        characterListInEpisodeAdapter.updateCharacterListString(episodeDetail.characters)
    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in EpisodeDetailsFragment ")
        with(binding.recyclerViewEpisodes) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = characterListInEpisodeAdapter
        }
    }

    private fun visibilityToolBar(){
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