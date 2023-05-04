package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.characters.detail.mapper.CharacterDetailDataToCharacterDetailDomainMapper
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.data.episodes.detail.mapper.EpisodeDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.episodes.list.mapper.EpisodeDataToListSingleEpisodeDomainMapper
import com.example.rickandmorty.data.locations.detail.mapper.LocationDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.locations.list.mapper.LocationListDataToLocationListDomainMapper
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    //Маппер для списка персонажей из дата слоя в домейн
    @Provides
    fun provideCharactersDataToListSingleCharacterDomainMapper(): CharactersDataToListSingleCharacterDomainMapper {
        return CharactersDataToListSingleCharacterDomainMapper()
    }

    //Маппер для списка персонажей из домейн слоя в презентейшн
    @Provides
    fun provideSingleCharacterDomainToSingleCharacterUiMapper(): SingleCharacterDomainToSingleCharacterUiMapper {
        return SingleCharacterDomainToSingleCharacterUiMapper()
    }

    // Маппер для деталей персонажа из дата в домейн
    @Provides
    fun provideCharacterDetailDataToCharacterDetailDomainMapper(): CharacterDetailDataToCharacterDetailDomainMapper {
        return CharacterDetailDataToCharacterDetailDomainMapper()
    }

    // Маппер для деталей персонажа из домаин в презентейшн
    @Provides
    fun provideCharacterDetailDomainToCharacterDetailUiMapper(): CharacterDetailDomainToCharacterDetailUiMapper {
        return CharacterDetailDomainToCharacterDetailUiMapper()
    }

    //Маппер для списка еризодов из дата в домаин
    @Provides
    fun provideEpisodeDataToListSingleEpisodeDomainMapper(): EpisodeDataToListSingleEpisodeDomainMapper {
        return EpisodeDataToListSingleEpisodeDomainMapper()
    }

    //Маппер для списка упизода из домейн слоя в презентейшн
    @Provides
    fun provideSingleEpisodeDomainToSingleEpisodeUiMapper(): SingleEpisodeDomainToSingleEpisodeUiMapper {
        return SingleEpisodeDomainToSingleEpisodeUiMapper()
    }

    // Маппер для деталей епизода из дата в домейн
    @Provides
    fun provideEpisodeDetailDataToEpisodeDetailDomainMapper(): EpisodeDetailDataToEpisodeDetailDomainMapper {
        return EpisodeDetailDataToEpisodeDetailDomainMapper()
    }

    // Маппер для деталей персонажа из домаин в презентейшн
    @Provides
    fun provideEpisodeDetailDomainToEpisodeDetailUiMapper(): EpisodeDetailDomainToEpisodeDetailUiMapper {
        return EpisodeDetailDomainToEpisodeDetailUiMapper()
    }

    //Маппер для списка локаций из дата в домейн
    @Provides
    fun provideLocationListDataToLocationListDomainMapper(): LocationListDataToLocationListDomainMapper {
        return LocationListDataToLocationListDomainMapper()
    }

    //Маппер для деталий локации из дата в домейн
    @Provides
    fun provideLocationDetailDataToEpisodeDetailDomainMapper(): LocationDetailDataToEpisodeDetailDomainMapper {
        return LocationDetailDataToEpisodeDetailDomainMapper()
    }

    // Маппер для списка локаций из домейн в презентейшен
    @Provides
    fun provideSingleLocationDomainToSingleLocationUiMapper(): SingleLocationDomainToSingleLocationUiMapper {
        return SingleLocationDomainToSingleLocationUiMapper()
    }

    // Маппер для деталей докации из домейн в презентер
    @Provides
    fun provideLocationDetailsDomainToLocationDetailsUIMapper(): LocationDetailsDomainToLocationDetailsUIMapper {
        return LocationDetailsDomainToLocationDetailsUIMapper()
    }


}