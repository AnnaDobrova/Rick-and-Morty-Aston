package com.example.rickandmorty.utils

sealed class AnnaResponse<T : Any> {
    data class Success<T : Any>(val data: T) : AnnaResponse<T>()
    data class Failure<T : Any>(val error: Throwable) : AnnaResponse<T>()
}
