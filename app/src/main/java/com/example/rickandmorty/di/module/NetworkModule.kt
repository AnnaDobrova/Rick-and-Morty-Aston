package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.characters.detail.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.episodes.detail.api.EpisodesDetailsNetworkDataSource
import com.example.rickandmorty.data.episodes.list.api.EpisodeNetworkDataSource
import com.example.rickandmorty.data.locations.detail.api.LocationDetailsNetworkDataSource
import com.example.rickandmorty.data.locations.list.api.LocationNetworkDataSours
import com.example.rickandmorty.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
class NetworkModule {

    /**
     * Тут происходит создание и запуск нашего ретрофита
     */
    @Provides
    @ActivityScope
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @ActivityScope
    fun provideOkHttp(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build();
    }

    /**
     * Тут мы говорим Ретрофиту что ему нужно работать с этим интерфейсом и его методами
     */
    @Provides
    @ActivityScope
    fun provideCharactersNetworkSource(
        retrofit: Retrofit
    ): CharactersNetworkDataSource = retrofit.create(CharactersNetworkDataSource::class.java)

    @Provides
    @ActivityScope
    fun provideCharacterDetailsNetworkDataSource(
        retrofit: Retrofit
    ): CharacterDetailsNetworkDataSource = retrofit.create(CharacterDetailsNetworkDataSource::class.java)

//TODO ДЛЯ ЕПИЗОДОВ

    @Provides
    @ActivityScope
    fun provideEpisodeNetworkDataSource(
        retrofit: Retrofit
    ): EpisodeNetworkDataSource = retrofit.create(EpisodeNetworkDataSource::class.java)

    @Provides
    @ActivityScope
    fun provideEpisodesDetailsNetworkDataSource(
        retrofit: Retrofit
    ): EpisodesDetailsNetworkDataSource = retrofit.create(EpisodesDetailsNetworkDataSource::class.java)


    //TODO ДЛЯ ЛОКАЦИЙ
    @Provides
    @ActivityScope
    fun provideLocationNetworkDataSours(
        retrofit: Retrofit
    ): LocationNetworkDataSours = retrofit.create(LocationNetworkDataSours::class.java)

    @Provides
    @ActivityScope
    fun provideLocationDetailsNetworkDataSource(
        retrofit: Retrofit
    ): LocationDetailsNetworkDataSource = retrofit.create(LocationDetailsNetworkDataSource::class.java)

}