package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterLocationData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterOriginData
import com.example.rickandmorty.data.local.characters.CharacterLocalDao
import com.example.rickandmorty.data.local.converter.Converters

@Database(
    entities = [
        SingleCharacterData::class,
        SingleCharacterLocationData::class,
        SingleCharacterOriginData::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterListDao(): CharacterLocalDao
}