package com.example.rickandmorty.di.module

import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCaseImpl
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.character.list.CharactersUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    @ActivityScope
    fun bindsCharactersUseCase(
        charactersUseCaseImpl: CharactersUseCaseImpl
    ): CharactersUseCase

    @Binds
    @ActivityScope
    fun bindsCharacterDetailUseCase(
        characterDetailUseCaseImpl: CharacterDetailUseCaseImpl
    ): CharacterDetailUseCase
}

