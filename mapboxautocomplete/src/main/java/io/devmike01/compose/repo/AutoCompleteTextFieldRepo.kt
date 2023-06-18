package io.devmike01.compose.repo
import io.devmike01.compose.models.SearchProperty
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class AutoCompleteTextFieldRepo @Inject constructor(private val service: MapboxService,
                                                    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {


    suspend fun queryMapbox(query: String, searchProperty: SearchProperty): SuggestionResponse {
        val deferred = CompletableDeferred<SuggestionResponse>()
        coroutineScope.launch {
            try {
                searchProperty.let {
                    val place = service.getPlace(query, country = it.country,
                        language = it.language,
                        latLongProximity = it.latLongProximity,
                        limit = it.limit,
                        radius = it.radius,
                        types = it.searchTypes.type,
                        userId = it.userId,
                        bbox = it.bbox,
                        origin = it.origin,
                    )
                    val nonEmptyAddresses = place.suggestions?.map { suggestion ->
                        val newPlace =if(suggestion.featureType == "poi"){
                            suggestion.fullAddress
                        }else{
                            suggestion.placeFormatted
                        }
                        suggestion.copy(fullAddress = newPlace)
                    }

                    deferred.complete(place.copy(suggestions = nonEmptyAddresses))
                }
            }catch (ex: Exception){
                deferred.completeExceptionally(ex)
            }
        }
        return deferred.await()
    }
}