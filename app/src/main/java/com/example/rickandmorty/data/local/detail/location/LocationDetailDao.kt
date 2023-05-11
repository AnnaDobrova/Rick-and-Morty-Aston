package com.example.rickandmorty.data.local.detail.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.data.locations.detail.model.LocationDetailData

@Dao
interface LocationDetailDao {

    @Query("SELECT * FROM LocationDetailEntity WHERE id=:id")
    suspend fun getSingleLocationDetail(id: Int): LocationDetailData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSingleLocationDetail(set: LocationDetailData)

    @Update
    suspend fun updateLocationDetail(locationList: List<LocationDetailData>)

    @Query("DELETE FROM LocationDetailEntity")
    suspend fun deleteLocationDetail()
}