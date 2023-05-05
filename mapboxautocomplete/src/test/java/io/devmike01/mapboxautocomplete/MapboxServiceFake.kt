package io.devmike01.mapboxautocomplete

import io.devmike01.mapboxautocomplete.models.Feature
import io.devmike01.mapboxautocomplete.models.Place
import io.devmike01.mapboxautocomplete.repo.MapboxService
import kotlinx.coroutines.CompletableDeferred

open class MapboxServiceFake() : MapboxService {
    override suspend fun getPlaceByName(placeName: String): Place {
        print("NEW_MESSAGE => TEAR DOWN!! $placeName")
        val deferred = CompletableDeferred<Place>()

        if(placeName.equals("-")){
            deferred.completeExceptionally(Exception("The API has failed"))
        }else{
            deferred.complete( Place(placeName = "Oshodi", features = listOf(Feature())))
        }
        return deferred.await()
    }
}