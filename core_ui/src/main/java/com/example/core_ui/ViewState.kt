package com.example.core_ui

sealed class ViewState<out T : Any>  {
    object Loading: ViewState<Nothing>()

    data class Data<out T : Any>(val data: T) : ViewState<T>()

    data class Error(val error: Throwable) : ViewState<Nothing>()
}