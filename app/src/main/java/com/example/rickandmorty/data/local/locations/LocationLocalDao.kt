package com.example.rickandmorty.data.local.locations

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.data.locations.list.model.SingleLocationData

@Dao
interface LocationLocalDao {

    @Query("SELECT * FROM LocationListEntity")
    suspend fun getLocationListData(): List<SingleLocationData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setLocationListData(locationLast: List<SingleLocationData>)

    @Update
    suspend fun updateLocationListData(locationLast: List<SingleLocationData>)

    @Delete
    suspend fun deleteLocationListData(locationLast: List<SingleLocationData>)
}