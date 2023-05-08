package com.example.rickandmorty.di.module

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.characters.CharacterLocalDao
import com.example.rickandmorty.data.local.AppDatabase
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
    fun provideCharacterListDao(appDatabase: AppDatabase): CharacterLocalDao = appDatabase.characterListDao()

}