package com.example.rickandmorty.di.module

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.data.local.list.characters.CharacterLocalDao
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.local.detail.character.CharacterDetailDao
import com.example.rickandmorty.data.local.detail.episode.EpisodeDetailDao
import com.example.rickandmorty.data.local.detail.location.LocationDetailDao
import com.example.rickandmorty.data.local.list.episodes.EpisodeLocalDao
import com.example.rickandmorty.data.local.list.locations.LocationLocalDao
import com.example.rickandmorty.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    @ActivityScope
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

    @Provides
    @ActivityScope
    fun provideCharacterLocalDao(appDatabase: AppDatabase): CharacterLocalDao =
        appDatabase.characterListDao()

    @Provides
    @ActivityScope
    fun provideEpisodeLocalDao(appDatabase: AppDatabase): EpisodeLocalDao =
        appDatabase.episodeListDao()

    @Provides
    @ActivityScope
    fun provideLocationLocalDao(appDatabase: AppDatabase): LocationLocalDao =
        appDatabase.locationListDao()

    @Provides
    @ActivityScope
    fun provideCharacterDetailLocalDao(appDatabase: AppDatabase): CharacterDetailDao =
        appDatabase.characterDetailDao()

    @Provides
    @ActivityScope
    fun provideEpisodeDetailLocalDao(appDatabase: AppDatabase): EpisodeDetailDao =
        appDatabase.episodeDetailDao()

    @Provides
    @ActivityScope
    fun provideLocationDetailLocalDao(appDatabase: AppDatabase): LocationDetailDao =
        appDatabase.locationDetailDao()
}