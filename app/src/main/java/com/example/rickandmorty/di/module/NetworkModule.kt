package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
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
}