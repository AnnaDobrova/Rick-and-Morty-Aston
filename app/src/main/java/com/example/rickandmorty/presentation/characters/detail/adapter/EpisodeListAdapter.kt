package com.example.rickandmorty.presentation.characters.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

class EpisodeListAdapter(private val episodeListDetailsListener: EpisodeListDetailsListener) :
    RecyclerView.Adapter<CharacterDetailsEpisodeViewHolder>() {

    private val episodeList = mutableListOf<SingleEpisodeUI>()
    private var episodeListString = mutableListOf<String>()

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
        val episode = episodeListString[position]
        holder.bindEpisodeString(episode)
    }

    override fun getItemCount(): Int {
        return episodeListString.size
    }

    fun updateEpisodeList(newEpisodeList: List<SingleEpisodeUI>) {
        episodeList.clear()
        episodeList.addAll(newEpisodeList)
        notifyDataSetChanged()
    }

    fun updateEpisodeListString(newEpisodeList: List<String>) {
        episodeListString.clear()
        episodeListString.addAll(newEpisodeList)
        notifyDataSetChanged()
    }

    fun addEpisode(episode: SingleEpisodeUI) {
        episodeList.add(episode)
        notifyDataSetChanged()
    }

}