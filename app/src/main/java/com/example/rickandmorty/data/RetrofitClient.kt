package com.example.rickandmorty.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

/**
 * В этом объекте мы создаем наш ректрофит
 */
object RetrofitClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * Тут происходит создание и запуск нашего ретрофита
     */
    fun fillRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create()) // конвертер которые преобразует json в дата класс и наоборот
            .baseUrl(BASE_URL) // тут мы указываем урл с которым мы будем работать
            .build() // аналог коммита у фрагментов
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build();
    }
}