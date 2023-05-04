package io.devmike01.mapboxautocomplete.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.EntryPoints
import io.devmike01.mapboxautocomplete.models.Place
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class CoreController<AutocompleteState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    protected fun runOnUi(errorHandler: CoroutineExceptionHandler, operation: suspend () -> Unit){
        coroutineScope.launch(errorHandler) {
            operation.invoke()
        }
    }
    abstract val state : StateFlow<AutocompleteState>
    abstract fun queryMapbox(query: String)
}