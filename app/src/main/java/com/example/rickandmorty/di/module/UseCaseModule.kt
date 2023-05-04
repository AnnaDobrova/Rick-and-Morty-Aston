package com.example.rickandmorty.di.module

import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCaseImpl
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.character.list.CharactersUseCaseImpl
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCaseImpl
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.domain.episode.list.EpisodeUseCaseImpl
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCaseImpl
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.domain.location.list.LocationUseCaseImpl
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


    @Binds
    @ActivityScope
    fun bindsEpisodeUseCase(
        episodeUseCaseImpl: EpisodeUseCaseImpl
    ): EpisodeUseCase

    @Binds
    @ActivityScope
    fun bindsEpisodeDetailUseCase(
        episodeDetailUseCaseImpl: EpisodeDetailUseCaseImpl
    ): EpisodeDetailUseCase

    @Binds
    @ActivityScope
    fun bindsLocationUseCase(
        locationUseCaseImpl: LocationUseCaseImpl
    ): LocationUseCase

    @Binds
    @ActivityScope
    fun bindsLocationDetailUseCase(
        locationDetailUseCaseImpl: LocationDetailUseCaseImpl
    ): LocationDetailUseCase
}

