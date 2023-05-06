package com.example.rickandmorty.utils.inteceptor

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager

class ChukerInterceptor(
    private val context: Context
) {

    fun intercept() = ChuckerInterceptor.Builder(context)
        // The previously created Collector
        .collector(createCollector())
        // The max body content length in bytes, after this responses will be truncated.
        .maxContentLength(250_000L)
        // Read the whole response body even when the client does not consume the response completely.
        // This is useful in case of parsing errors or when the response body
        // is closed before being read like in Retrofit with Void and Unit types.
        .alwaysReadResponseBody(true)
        .build()

    private fun createCollector() = ChuckerCollector(
        context = context,
        // Toggles visibility of the notification
        showNotification = true,
        // Allows to customize the retention period of collected data
        retentionPeriod = RetentionManager.Period.ONE_DAY
    )
}