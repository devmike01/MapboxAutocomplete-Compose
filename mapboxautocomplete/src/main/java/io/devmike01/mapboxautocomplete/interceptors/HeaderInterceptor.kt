package io.devmike01.mapboxautocomplete.interceptors

import android.util.Log
import io.devmike.compose.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.UUID


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val uuid = UUID.randomUUID()
        val url: HttpUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("access_token", BuildConfig.MAPBOX_TOKEN)
            .addQueryParameter("session_token", uuid.toString())
            .build()
        val sanitisedUrl = getSanitisedUrl(url)
        val newRequest = request.newBuilder().url(sanitisedUrl).build()
        return chain.proceed(newRequest)
    }

    private fun getSanitisedUrl(httpUrl: HttpUrl): HttpUrl{
        val url = httpUrl.toUrl()
        var query = url.query
        val urlQueries = query.split("=")
        urlQueries.forEachIndexed{i, it ->
            if(it.startsWith("&") || it == ""){
                val queryToRemove = urlQueries[i-1]
                val unwantedKey = queryToRemove.split("&")[1]
                query = query.replace("&$unwantedKey=", "")
                //println(url.elementAt(removeIndex))
            }
        }
        return HttpUrl.Builder().scheme("https").host(url.host).encodedPath(url.path).query(query).build()
    }
}