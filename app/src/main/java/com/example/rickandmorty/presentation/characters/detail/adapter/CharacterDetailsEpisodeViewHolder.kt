package com.example.rickandmorty.presentation.characters.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.EpisodeBinding
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

class CharacterDetailsEpisodeViewHolder(
    itemView: View,
    private val episodeListDetailsListener: EpisodeListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding = EpisodeBinding.bind(itemView)

    fun bindEpisode(episode: SingleEpisodeUI) {
        with(binding) {
            idEpisode.text = episode.id.toString()
            nameEpisode.text = episode.nameEpisode
            numberEpisode.text = episode.numberEpisode
            airDataEpisode.text = episode.airDataEpisode
        }
        itemView.setOnClickListener {
            episodeListDetailsListener.goToDetailsEpisode(episode)
        }
    }
}