package io.devmike01.mapboxautocomplete

import io.devmike01.mapboxautocomplete.models.Feature
import io.devmike01.mapboxautocomplete.models.Place
import io.devmike01.mapboxautocomplete.repo.MapboxService
import io.devmike01.mapboxautocomplete.repo.Suggestion
import io.devmike01.mapboxautocomplete.repo.SuggestionResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay

open class MapboxServiceFake : MapboxService {
    override suspend fun getPlace(
        placeName: String,
        language: String,
        limit: Int,
        latLongProximity: String,
        origin: String,
        bbox: String,
        country: String,
        types: String,
        radius: String,
        userId: String
    ): SuggestionResponse {
        val deferred = CompletableDeferred<SuggestionResponse>()
        if(placeName.equals("-")){
            deferred.completeExceptionally(Exception("The API has failed"))
        }else{
            deferred.complete( SuggestionResponse( suggestions = listOf(Suggestion(fullAddress = "5 Oshodi road, Apapa, Lagos state, Nigeria", featureType = "poi"),
                Suggestion(placeFormatted = "Ikeja Shopping Mall", featureType = "place"))))
        }
        return deferred.await()
    }

}