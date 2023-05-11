package com.example.rickandmorty.data.local.list.episodes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData

@Dao
interface EpisodeLocalDao {

    @Query("SELECT * FROM EpisodeListEntity")
    suspend fun getEpisodeListData(): List<SingleEpisodeListData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setEpisodeListData(episodeList: List<SingleEpisodeListData>)

    @Update
    suspend fun updateEpisodeListData(episodeList: List<SingleEpisodeListData>)

    @Delete
    suspend fun deleteEpisodeListData(episodeList: List<SingleEpisodeListData>)
}