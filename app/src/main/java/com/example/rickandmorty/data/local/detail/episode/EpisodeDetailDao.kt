package com.example.rickandmorty.data.local.detail.episode

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData

@Dao
interface EpisodeDetailDao {

    @Query("SELECT * FROM SingleEpisodeDetailEntity WHERE id=:id")
    suspend fun getSingleEpisodeDetail(id: Int): SingleEpisodeDetailData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSingleEpisodeDetail(set: SingleEpisodeDetailData)

    @Update
    suspend fun updateCharacterDetail(characterList: List<SingleEpisodeDetailData>)

    @Query("DELETE FROM SingleEpisodeDetailEntity")
    suspend fun deleteSingleEpisodeDetail()
}