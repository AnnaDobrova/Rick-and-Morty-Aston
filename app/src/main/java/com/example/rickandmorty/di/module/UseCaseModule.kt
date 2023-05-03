package com.example.rickandmorty.di.module

import com.example.rickandmorty.di.scope.ActivityScope
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
}