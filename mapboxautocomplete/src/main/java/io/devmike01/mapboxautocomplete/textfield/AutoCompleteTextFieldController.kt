package io.devmike01.mapboxautocomplete.textfield

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devmike01.mapboxautocomplete.models.Place
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AutoCompleteTextFieldController (private val autocompleteRepo: AutoCompleteTextFieldRepo) : CoreController<AutocompleteState>() {

    val innerState = MutableStateFlow<AutocompleteState>(AutocompleteState())

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
            innerState.update {
                it.copy(place = PlaceState.Success(place))
            }
        }
    }

}