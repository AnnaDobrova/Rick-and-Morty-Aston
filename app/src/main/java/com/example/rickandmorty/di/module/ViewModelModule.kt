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
import com.example.rickandmorty.utils.Connectivity
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
        singleCharacterDomainToSingleCharacterUiMapper: SingleCharacterDomainToSingleCharacterUiMapper,
        connectivity: Connectivity
    ): ViewModel {
        return CharactersViewModel(
            charactersUseCase,
            singleCharacterDomainToSingleCharacterUiMapper,
            connectivity
        )
    }

    @IntoMap
    @ViewModelKey(CharactersDetailViewModel::class)
    @Provides
    fun provideCharactersDetailViewModel(
        characterDetailUseCase: CharacterDetailUseCase,
        episodeDetailUseCase: EpisodeDetailUseCase,
        characterDetailDomainToCharacterDetailUiMapper: CharacterDetailDomainToCharacterDetailUiMapper,
        episodeDetailDomainToEpisodeDetailUiMapper: EpisodeDetailDomainToEpisodeDetailUiMapper
    ): ViewModel {
        return CharactersDetailViewModel(
            characterDetailUseCase,
            episodeDetailUseCase = episodeDetailUseCase,
            characterDetailDomainToCharacterDetailUiMapper,
            mapperFromDomainToUiForEpisodes = episodeDetailDomainToEpisodeDetailUiMapper
        )
    }

    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    @Provides
    fun provideEpisodesViewModel(
        episodeUseCase: EpisodeUseCase,
        singleEpisodeDomainToSingleEpisodeUiMapper: SingleEpisodeDomainToSingleEpisodeUiMapper,
        connectivity: Connectivity
    ): ViewModel {
        return EpisodesViewModel(
            episodeUseCase,
            singleEpisodeDomainToSingleEpisodeUiMapper,
            connectivity
        )
    }

    @IntoMap
    @ViewModelKey(EpisodeDetailViewModel::class)
    @Provides
    fun provideEpisodeDetailViewModel(
        episodeDetailUseCase: EpisodeDetailUseCase,
        characterDetailUseCase: CharacterDetailUseCase,
        episodeDetailDomainToCharacterDetailUiMapper: EpisodeDetailDomainToEpisodeDetailUiMapper,
        characterDetailDomainToCharacterDetailUiMapper: CharacterDetailDomainToCharacterDetailUiMapper
    ): ViewModel {
        return EpisodeDetailViewModel(
            episodeDetailUseCase,
            characterDetailUseCase,
            episodeDetailDomainToCharacterDetailUiMapper,
            characterDetailDomainToCharacterDetailUiMapper
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
        characterDetailUseCase: CharacterDetailUseCase,
        locationDetailsDomainToLocationDetailsUIMapper: LocationDetailsDomainToLocationDetailsUIMapper,
        characterDetailDomainToCharacterDetailUiMapper: CharacterDetailDomainToCharacterDetailUiMapper
    ): ViewModel {
        return LocationDetailViewModel(
            locationDetailUseCase,
            characterDetailUseCase,
            locationDetailsDomainToLocationDetailsUIMapper,
            characterDetailDomainToCharacterDetailUiMapper
        )
    }
}