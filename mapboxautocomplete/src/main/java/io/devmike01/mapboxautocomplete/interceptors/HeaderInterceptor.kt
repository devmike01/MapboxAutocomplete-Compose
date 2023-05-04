package io.devmike01.mapboxautocomplete.interceptors

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url: HttpUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("access_token", "pk.eyJ1IjoiZGV2bWlrZTAxIiwiYSI6ImNsYWUyNTBicDBjYXQzd282NXVsNDZndnoifQ.5W3CyVpE0VkQwVO0mVxiSw")
            .build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}