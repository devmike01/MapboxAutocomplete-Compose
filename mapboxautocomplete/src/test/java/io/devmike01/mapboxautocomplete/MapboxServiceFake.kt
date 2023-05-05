package io.devmike01.mapboxautocomplete

import io.devmike01.mapboxautocomplete.models.Feature
import io.devmike01.mapboxautocomplete.models.Place
import io.devmike01.mapboxautocomplete.repo.MapboxService

class MapboxServiceFake() : MapboxService {
    override suspend fun getPlaceByName(placeName: String): Place {
        print("NEW_MESSAGE => TEAR DOWN!! $placeName")

        return if(placeName.equals("-")){
            throw Exception("The API has failed")
        }else{
            Place(placeName = "Oshodi", features = listOf(Feature()))
        }
    }
}