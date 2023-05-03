package com.example.rickandmorty.di.module

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.key.ViewModelKey
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.presentation.characters.list.CharactersViewModel
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
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
}