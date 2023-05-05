package io.devmike01.mapboxautocomplete.textfield

import io.devmike01.mapboxautocomplete.models.Place

data class AutocompleteState(val place: PlaceState = PlaceState.Nothing)

sealed interface PlaceState{
    data class Success(val data: Place): PlaceState
    data class Error(val error : Throwable): PlaceState
    object Nothing : PlaceState
}