package com.example.rickandmorty.data.local.characters

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData

@Dao
interface CharacterLocalDao {

    @Query("SELECT * FROM CharacterListEntity")
    fun getCharacterList(): PagingSource<Int, SingleCharacterData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCharacterList(characterList: List<SingleCharacterData>)

    @Update
    suspend fun updateCharacterList(characterList: List<SingleCharacterData>)

    @Query("DELETE FROM CharacterListEntity")
    suspend fun deleteCharacterList()
}