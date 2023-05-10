package com.example.rickandmorty.di.module

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.characters.CharacterLocalDao
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.local.episodes.EpisodeLocalDao
import com.example.rickandmorty.data.local.locations.LocationLocalDao
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
}