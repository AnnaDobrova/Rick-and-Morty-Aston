package com.example.rickandmorty.presentation.characters.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi

class EpisodeListAdapter(private val episodeListDetailsListener: EpisodeListDetailsListener) :
    RecyclerView.Adapter<CharacterDetailsEpisodeViewHolder>() {

    private val episodeList = mutableListOf<EpisodeDetailUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailsEpisodeViewHolder {
        return CharacterDetailsEpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.episode,
                parent,
                false
            ),
            episodeListDetailsListener
        )
    }

    override fun onBindViewHolder(holder: CharacterDetailsEpisodeViewHolder, position: Int) {
        val episode = episodeList[position]
        holder.bindEpisode(episode)
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }

    fun updateEpisodeList(newEpisodeList: List<EpisodeDetailUi>) {
        episodeList.clear()
        episodeList.addAll(newEpisodeList)
        notifyDataSetChanged()
    }
}