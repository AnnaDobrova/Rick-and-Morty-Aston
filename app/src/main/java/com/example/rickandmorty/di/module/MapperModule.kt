package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
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
}