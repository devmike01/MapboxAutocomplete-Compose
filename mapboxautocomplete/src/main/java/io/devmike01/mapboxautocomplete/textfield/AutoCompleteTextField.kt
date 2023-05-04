package io.devmike01.mapboxautocomplete.textfield

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.devmike01.mapboxautocomplete.di.provideAutoCompleteController
import io.devmike01.mapboxautocomplete.models.Feature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AutoCompleteTextField(modifier: Modifier = Modifier,
                          onValueChanged:((String) -> Unit)? =null,
                          autocomplete: MutableState<String>,
                          isError: Boolean = false,
                          enable: Boolean =true,
                          maxLines: Int = 1,
                          keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                          visualTransformation: VisualTransformation = VisualTransformation.None,
                          label: @Composable (() -> Unit)? = null,
                          hint: @Composable (() -> Unit)? = null,
                          content: @Composable ((Feature) -> Unit)? =null,
                          onError: @Composable ((Throwable) -> Unit)? =null,
                          onSelectItem: ((Feature) -> Unit)? =null,
) {

    Column(modifier = modifier) {
        TextField(
            value = autocomplete.value,
            onValueChange = {
                autocomplete.value =it
                onValueChanged?.invoke(it)
            },
            label = label,
            maxLines = maxLines,
            enabled = enable,
            placeholder =hint,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),//.padding(10.dp),
            shape = RoundedCornerShape(8.dp),
            isError =isError,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
                focusedLabelColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primaryVariant
            )
        )

        val autocompleteViewModel: CoreController<AutocompleteState> = provideAutoCompleteController()
        autocomplete.value
        autocompleteViewModel.queryMapbox(autocomplete.value)

        AutoCompleteContent(autocompleteViewModel.state, onError = onError,
            content = content, onSelectItem = onSelectItem, )
    }

}

@Composable
fun AutoCompleteContent(stateFlow: StateFlow<AutocompleteState>,
                        content: @Composable ((Feature) -> Unit)? =null,
                        onError: @Composable ((Throwable) -> Unit)? =null,
                        onSelectItem: ((Feature) -> Unit)? =null){
    //Log.d("AutoCompViewModel", ""+ vmState)

    val vmState =stateFlow.collectAsState()

    when(val state = vmState.value.place){
        is PlaceState.Success ->{
            Log.d("AutoCompViewModel", "${state}")
            LazyColumn(modifier =
            Modifier
                .background(color = MaterialTheme.colors.background)
                .shadow(elevation = 3.dp)
                .wrapContentHeight()
                .fillMaxWidth()
            ) {
                val places = state.data.features
                items(places.size) {
                    if (content == null){
                        PlaceItem(places[it],
                            it == places.size-1,
                            onSelectItem = onSelectItem)
                        return@items
                    }
                    content.invoke(places[it])
                }
            }
        }
        is PlaceState.Error ->{
            Log.d("AutoCompViewModel01", "${state.error}")
            onError?.invoke(state.error)
        }
        else ->{}
    }
}

@Composable
fun PlaceItem(feature: Feature, isLast: Boolean,
              onSelectItem: ((Feature) -> Unit)?){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            onSelectItem?.invoke(feature)
        },
        verticalArrangement = Arrangement.Center
    ) {
        Text(feature.placeName ?: "", modifier = Modifier
            .padding(10.dp))
        if(!isLast){
            Divider()
        }
    }
}