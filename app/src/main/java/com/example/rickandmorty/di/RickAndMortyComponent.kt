package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.di.module.MapperModule
import com.example.rickandmorty.di.module.NetworkModule
import com.example.rickandmorty.di.module.RepositoryModule
import com.example.rickandmorty.di.module.UseCaseModule
import com.example.rickandmorty.di.module.ViewModelModule
import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailsFragment
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment
import com.example.rickandmorty.presentation.episodes.details.EpisodeDetailsFragment
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFragment
import com.example.rickandmorty.presentation.locations.detail.LocationDetailsFragment
import com.example.rickandmorty.presentation.locations.list.LocationListFragment
import com.example.rickandmorty.utils.ViewModelFactory
import dagger.BindsInstance
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
    fun inject(episodeListFragment: EpisodeListFragment)
    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)
    fun inject(locationListFragment: LocationListFragment)
    fun inject(locationDetailsFragment: LocationDetailsFragment)

    @Component.Factory
    interface RickAndMortyComponentFactory {
        fun create(@BindsInstance context: Context): RickAndMortyComponent
    }

    fun getViewModelFactory(): ViewModelFactory
}