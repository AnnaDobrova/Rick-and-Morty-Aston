package com.example.rickandmorty.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
            .addConverterFactory(GsonConverterFactory.create()) // конвертер которые преобразует json в дата класс и наоборот
            .baseUrl(BASE_URL) // тут мы указываем урл с которым мы будем работать
            .build() // аналог коммита у фрагментов
    }
}