package io.devmike01.mapboxautocomplete.repo

import io.devmike01.mapboxautocomplete.models.Place
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

// geocoding/v5/mapbox.places

interface MapboxService {

    @GET(MapboxEndpoints.PLACES +"/{long},{lat}.json")
    suspend fun getPlaces(@Path("lat") lat: Double, @Path("long")long: Double) : Place


    @GET(MapboxEndpoints.PLACES +"/{place_name}.json")
    suspend fun getPlaceByName(@Path("place_name") placeName: String) : Place

}