package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailLocationData
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailOriginData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterLocationData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterOriginData
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.data.local.converter.Converters
import com.example.rickandmorty.data.local.detail.character.CharacterDetailDao
import com.example.rickandmorty.data.local.detail.episode.EpisodeDetailDao
import com.example.rickandmorty.data.local.detail.location.LocationDetailDao
import com.example.rickandmorty.data.local.list.characters.CharacterLocalDao
import com.example.rickandmorty.data.local.list.episodes.EpisodeLocalDao
import com.example.rickandmorty.data.local.list.locations.LocationLocalDao
import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.data.locations.list.model.SingleLocationData

@Database(
    entities = [
        SingleCharacterData::class,
        SingleCharacterLocationData::class,
        SingleCharacterOriginData::class,
        SingleEpisodeListData::class,
        SingleLocationData::class,
        CharacterDetailLocationData::class,
        CharacterDetailOriginData::class,
        CharacterDetailData::class,
        SingleEpisodeDetailData::class,
        LocationDetailData::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterListDao(): CharacterLocalDao
    abstract fun episodeListDao(): EpisodeLocalDao
    abstract fun locationListDao(): LocationLocalDao

    abstract fun characterDetailDao(): CharacterDetailDao
    abstract fun episodeDetailDao(): EpisodeDetailDao
    abstract fun locationDetailDao(): LocationDetailDao

}