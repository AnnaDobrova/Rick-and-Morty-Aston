package com.example.rickandmorty.utils

import retrofit2.Response as RetroResponse

fun <Api : Any, Domain : Any> RetroResponse<Api>.tryMap(
    mapSuccess: (Api) -> AnnaResponse.Success<Domain>,
    mapFailure: ((RetroResponse<Api>) -> AnnaResponse<Domain>)? = null
): AnnaResponse<Domain> {
    val body = body()

    return if (body != null) mapSuccess(body)
    else mapFailure?.invoke(this)
        ?: AnnaResponse.Failure(Throwable())
}

suspend fun <Api : Any, Domain : Any> tryMapSuspended(
    request: suspend () -> RetroResponse<Api>,
    mapSuccess: (Api) -> AnnaResponse.Success<Domain>,
    mapFailure: ((RetroResponse<Api>) -> AnnaResponse<Domain>)? = null,
    mapException: ((Exception) -> AnnaResponse<Domain>)? = null
): AnnaResponse<Domain> = try {

    request().tryMap(mapSuccess, mapFailure)

} catch (ex: Exception) {
    mapException?.invoke(ex)
        ?: AnnaResponse.Failure(ex)
}