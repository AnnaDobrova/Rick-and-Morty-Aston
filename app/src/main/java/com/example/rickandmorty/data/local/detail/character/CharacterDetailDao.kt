package com.example.rickandmorty.data.local.detail.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData

@Dao
interface CharacterDetailDao {

    @Query("SELECT * FROM CharacterDetailEntity WHERE characterDetailId=:id")
    suspend fun getCharacterDetail(id: Int): CharacterDetailData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCharacterDetail(set: CharacterDetailData)

    @Update
    suspend fun updateCharacterDetail(characterList: List<CharacterDetailData>)

    @Query("DELETE FROM CharacterDetailEntity")
    suspend fun deleteCharacterDetail()
}