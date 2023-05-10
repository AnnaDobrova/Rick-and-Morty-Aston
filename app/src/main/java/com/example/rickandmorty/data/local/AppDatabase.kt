package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterLocationData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterOriginData
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.data.local.characters.CharacterLocalDao
import com.example.rickandmorty.data.local.converter.Converters
import com.example.rickandmorty.data.local.episodes.EpisodeLocalDao
import com.example.rickandmorty.data.local.locations.LocationLocalDao
import com.example.rickandmorty.data.locations.list.model.SingleLocationData

@Database(
    entities = [
        SingleCharacterData::class,
        SingleCharacterLocationData::class,
        SingleCharacterOriginData::class,
        SingleEpisodeListData::class,
        SingleLocationData::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterListDao(): CharacterLocalDao
    abstract fun episodeListDao(): EpisodeLocalDao
    abstract fun locationListDao(): LocationLocalDao
}