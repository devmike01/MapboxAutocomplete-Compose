package io.devmike01.mapboxautocomplete.textfield

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class CoreController<AutocompleteState>(private val coroutineContext: CoroutineContext) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    var job: Job? =null
    protected fun runOnUi(errorHandler: CoroutineExceptionHandler, operation: suspend () -> Unit){
        job =coroutineScope.launch(context = coroutineContext) {
            try {
                operation.invoke()
            }catch (exception: Exception){
                errorHandler.handleException(context = coroutineContext, exception = exception)
            }
        }
    }
    abstract val state : StateFlow<AutocompleteState>
    abstract fun queryMapbox(query: String)

    fun onClose(){
        job?.cancel()
    }

}