package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.characters.detail.CharacterDetailsRepositoryImpl
import com.example.rickandmorty.data.characters.list.CharactersRepositoryImpl
import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import com.example.rickandmorty.domain.character.list.CharactersRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @ActivityScope
    fun bindsCharactersRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository

    @Binds
    @ActivityScope
    fun bindsCharacterDetailRepository(
        characterDetailsRepositoryImpl: CharacterDetailsRepositoryImpl
    ): CharacterDetailRepository
}
