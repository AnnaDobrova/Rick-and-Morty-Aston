package com.example.rickandmorty.di

import com.example.rickandmorty.di.module.MapperModule
import com.example.rickandmorty.di.module.NetworkModule
import com.example.rickandmorty.di.module.RepositoryModule
import com.example.rickandmorty.di.module.UseCaseModule
import com.example.rickandmorty.di.module.ViewModelModule
import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailsFragment
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment
import com.example.rickandmorty.utils.ViewModelFactory
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        MapperModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
@ActivityScope
interface RickAndMortyComponent {

    fun inject(characterListFragment: CharacterListFragment)
    fun inject(characterDetailFragment: CharacterDetailsFragment)

    @Component.Factory
    interface RickAndMortyComponentFactory {
        fun create(): RickAndMortyComponent
    }

    fun getViewModelFactory(): ViewModelFactory
}