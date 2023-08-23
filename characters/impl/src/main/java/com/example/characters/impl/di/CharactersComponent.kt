package com.example.characters.impl.di

import com.example.characters.api.di.CharactersDependencies
import com.example.characters.impl.presentation.detail.CharacterDetailsFragment
import com.example.core_ui.ViewModelFactory
import com.example.characters.impl.presentation.list.CharacterListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CharactersDependencies::class]
)
interface CharactersComponent {

    fun inject(characterListFragment: CharacterListFragment)
    fun inject(characterDetailFragment: CharacterDetailsFragment)

    @Component.Factory
    interface CharactersComponentFactory {
        fun create(
            @BindsInstance charactersDependencies: CharactersDependencies
        ): CharactersComponent
    }

    fun getViewModelFactory(): ViewModelFactory
}