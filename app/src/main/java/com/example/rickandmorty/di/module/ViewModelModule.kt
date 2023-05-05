package com.example.rickandmorty.di.module

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.key.ViewModelKey
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.presentation.characters.detail.CharactersDetailViewModel
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.list.CharactersViewModel
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.episodes.details.EpisodeDetailViewModel
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.list.EpisodesViewModel
import com.example.rickandmorty.presentation.episodes.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.locations.detail.LocationDetailViewModel
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.list.LocationsViewModel
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    @Provides
    fun provideCharactersViewModel(
        charactersUseCase: CharactersUseCase,
        singleCharacterDomainToSingleCharacterUiMapper: SingleCharacterDomainToSingleCharacterUiMapper
    ): ViewModel {
        return CharactersViewModel(
            charactersUseCase,
            singleCharacterDomainToSingleCharacterUiMapper
        )
    }

    @IntoMap
    @ViewModelKey(CharactersDetailViewModel::class)
    @Provides
    fun provideCharactersDetailViewModel(
        characterDetailUseCase: CharacterDetailUseCase,
        characterDetailDomainToCharacterDetailUiMapper: CharacterDetailDomainToCharacterDetailUiMapper
    ): ViewModel {
        return CharactersDetailViewModel(
            characterDetailUseCase,
            characterDetailDomainToCharacterDetailUiMapper
        )
    }

    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    @Provides
    fun provideEpisodesViewModel(
        episodeUseCase: EpisodeUseCase,
        singleEpisodeDomainToSingleEpisodeUiMapper: SingleEpisodeDomainToSingleEpisodeUiMapper
    ): ViewModel {
        return EpisodesViewModel(
            episodeUseCase,
            singleEpisodeDomainToSingleEpisodeUiMapper
        )
    }


    @IntoMap
    @ViewModelKey(EpisodeDetailViewModel::class)
    @Provides
    fun provideEpisodeDetailViewModel(
        episodeDetailUseCase: EpisodeDetailUseCase,
        episodeDetailDomainToCharacterDetailUiMapper: EpisodeDetailDomainToEpisodeDetailUiMapper
    ): ViewModel {
        return EpisodeDetailViewModel(
            episodeDetailUseCase,
            episodeDetailDomainToCharacterDetailUiMapper
        )
    }
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    @Provides
    fun provideLocationsViewModel(
        locationUseCase: LocationUseCase,
        singleLocationDomainToSingleLocationUiMapper: SingleLocationDomainToSingleLocationUiMapper
    ): ViewModel {
        return LocationsViewModel(
            locationUseCase,
            singleLocationDomainToSingleLocationUiMapper
        )
    }


    @IntoMap
    @ViewModelKey(LocationDetailViewModel::class)
    @Provides
    fun provideLocationDetailViewModel(
        locationDetailUseCase: LocationDetailUseCase,
        locationDetailsDomainToLocationDetailsUIMapper: LocationDetailsDomainToLocationDetailsUIMapper
    ): ViewModel {
        return LocationDetailViewModel(
            locationDetailUseCase,
            locationDetailsDomainToLocationDetailsUIMapper
        )
    }
}