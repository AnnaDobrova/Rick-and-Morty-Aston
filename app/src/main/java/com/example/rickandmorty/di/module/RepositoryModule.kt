package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.characters.detail.CharacterDetailsRepositoryImpl
import com.example.rickandmorty.data.characters.list.CharactersRepositoryImpl
import com.example.rickandmorty.data.episodes.detail.EpisodeDetailsRepositoryImpl
import com.example.rickandmorty.data.episodes.list.EpisodeRepositoryImpl
import com.example.rickandmorty.data.locations.LocationsRepositoryImpl
import com.example.rickandmorty.data.locations.detail.LocationDetailsRepositoryImpl
import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.episode.details.EpisodeDetailRepository
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import com.example.rickandmorty.domain.location.detail.LocationDetailRepository
import com.example.rickandmorty.domain.location.list.LocationRepository
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

    @Binds
    @ActivityScope
    fun bindsEpisodeRepository(
        episodeRepositoryImpl: EpisodeRepositoryImpl
    ): EpisodesRepository

    @Binds
    fun bindsEpisodeDetailRepository(
        episodeDetailsRepositoryImpl: EpisodeDetailsRepositoryImpl
    ): EpisodeDetailRepository


    @Binds
    @ActivityScope
    fun bindsLocationRepository(
        locationsRepositoryImpl: LocationsRepositoryImpl
    ): LocationRepository

    @Binds
    @ActivityScope
    fun bindsLocationDetailRepository(
        locationDetailsRepositoryImpl: LocationDetailsRepositoryImpl
    ): LocationDetailRepository

}
