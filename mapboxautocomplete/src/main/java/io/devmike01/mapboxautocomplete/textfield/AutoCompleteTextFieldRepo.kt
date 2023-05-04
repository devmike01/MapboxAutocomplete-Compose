package io.devmike01.mapboxautocomplete.textfield
import io.devmike01.mapboxautocomplete.models.Place
import io.devmike01.mapboxautocomplete.repo.MapboxService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AutoCompleteTextFieldRepo @Inject constructor(private val service: MapboxService,
                                                    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {

    suspend fun queryMapbox(query: String): Place {
        val deferred = CompletableDeferred<Place>()
        coroutineScope.launch {
            try {
                val place = service.getPlaceByName(query)
                deferred.complete(place)
            }catch (ex: Exception){
                deferred.completeExceptionally(ex)
            }
        }
        return deferred.await()
    }
}