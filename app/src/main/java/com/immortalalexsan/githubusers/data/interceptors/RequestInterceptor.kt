package com.immortalalexsan.githubusers.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Интерсептор запросов на сервер.
 */
internal class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("accept", "application/vnd.github.v3+json")
            .build()
        return chain.proceed(request)
    }
}
