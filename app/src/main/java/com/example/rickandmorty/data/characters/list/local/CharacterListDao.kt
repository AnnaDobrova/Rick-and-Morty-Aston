package com.example.rickandmorty.data.characters.list.local

import androidx.room.Dao
import androidx.room.Query
import com.example.rickandmorty.data.characters.list.local.model.CharacterListEntity

@Dao
interface CharacterListDao {

    @Query("SELECT * FROM CharacterListEntity")
    suspend fun getCharacterList(): List<CharacterListEntity>
}