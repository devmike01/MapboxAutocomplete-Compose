package io.devmike01.mapboxautocomplete.repo

import io.devmike01.mapboxautocomplete.models.Place
import retrofit2.http.GET
import retrofit2.http.Path

// geocoding/v5/mapbox.places

interface MapboxService {

    @GET(MapboxEndpoints.PLACES +"/{place_name}.json")
    suspend fun getPlaceByName(@Path("place_name") placeName: String) : Place

}