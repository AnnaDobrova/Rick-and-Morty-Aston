package com.example.rickandmorty.di

import android.content.Context
import com.example.characters.api.di.CharactersDependencies
import com.example.core_ui.ViewModelFactory
import com.example.rickandmorty.di.module.LocalModule
import com.example.rickandmorty.di.module.MapperModule
import com.example.rickandmorty.di.module.NetworkModule
import com.example.rickandmorty.di.module.RepositoryModule
import com.example.rickandmorty.di.module.UseCaseModule
import com.example.rickandmorty.di.module.ViewModelModule
import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.presentation.episodes.details.EpisodeDetailsFragment
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFragment
import com.example.rickandmorty.presentation.locations.detail.LocationDetailsFragment
import com.example.rickandmorty.presentation.locations.list.LocationListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        MapperModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        LocalModule::class
    ]
)
@ActivityScope
interface RickAndMortyComponent : CharactersDependencies {

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