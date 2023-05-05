package io.devmike01.mapboxautocomplete.textfield

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.coroutines.CoroutineContext

class AutoCompleteTextFieldController (private val autocompleteRepo: AutoCompleteTextFieldRepo,
                                       private val coroutineContext: CoroutineContext = Dispatchers.Main)
    : CoreController<AutocompleteState>(coroutineContext) {

    private val innerState = MutableStateFlow<AutocompleteState>(AutocompleteState())

    private val handler = CoroutineExceptionHandler { _, exception ->
        innerState.update {
            it.copy(place = PlaceState.Error(exception ))
        }
    }
    override val state: StateFlow<AutocompleteState>
        get() = innerState

    override fun queryMapbox(query: String) {

        if(query.isBlank())return
        runOnUi(handler) {
            val place = autocompleteRepo.queryMapbox(query)
            print("NEW_MESSAGE10 => $place")
            innerState.update {
                it.copy(place = PlaceState.Success(place))
            }
        }
    }

}