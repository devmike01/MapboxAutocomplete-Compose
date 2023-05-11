package io.devmike01.mapboxautocomplete.di

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.json.JsonReadFeature
import io.devmike.mapboxautocomplete.BuildConfig
import io.devmike01.mapboxautocomplete.di.MapModuleInjector.provideAutoCompleteTextFieldRepo
import io.devmike01.mapboxautocomplete.interceptors.HeaderInterceptor
import io.devmike01.mapboxautocomplete.interceptors.LogInterceptor
import io.devmike01.mapboxautocomplete.repo.MapboxService
import io.devmike01.mapboxautocomplete.textfield.AutoCompleteTextFieldController
import io.devmike01.mapboxautocomplete.repo.AutoCompleteTextFieldRepo
import io.devmike01.mapboxautocomplete.textfield.AutocompleteState
import io.devmike01.mapboxautocomplete.textfield.CoreController
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

object MapModuleInjector {

    fun provideIOContext(): CoroutineContext {
        return Dispatchers.IO
    }

    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(LogInterceptor())
            .build()
    }

    fun jackson() : JsonFactory = JsonFactory.builder() // configure, if necessary:
        .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
        .build()

    fun provideMapboxApi(): MapboxService{

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MAPBOX_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        return retrofit.create(MapboxService::class.java)
    }


    fun provideAutoCompleteTextFieldRepo(): AutoCompleteTextFieldRepo {
        return AutoCompleteTextFieldRepo(service = provideMapboxApi())
    }
}

fun provideAutoCompleteController(): CoreController<AutocompleteState> {
    return AutoCompleteTextFieldController(autocompleteRepo = provideAutoCompleteTextFieldRepo())
}