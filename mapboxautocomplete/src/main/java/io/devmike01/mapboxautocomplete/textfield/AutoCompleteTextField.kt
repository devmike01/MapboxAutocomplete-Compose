package io.devmike01.mapboxautocomplete.textfield

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.devmike01.mapboxautocomplete.di.provideAutoCompleteController
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import io.devmike01.mapboxautocomplete.models.SearchProperty
import io.devmike01.mapboxautocomplete.repo.Suggestion

@Composable
fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    onValueChanged: ((String) -> Unit)? = null,
    autocomplete: MutableState<String>,
    isError: Boolean = false,
    enable: Boolean = true,
    maxLines: Int = 1,
   // colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors() as ACTextFieldColors,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label: @Composable (() -> Unit)? = null,
    hint: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable ((Suggestion) -> Unit)? = null,
    onError: @Composable ((Throwable) -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onSelectItem: ((Suggestion) -> Unit)? = null,
    searchProperty: SearchProperty = SearchProperty(),
) {
    val showSuggestions = remember {
        mutableStateOf( false)
    }
    Column(modifier = modifier.background(Color.Transparent)) {
        OutlinedTextField(
            value = autocomplete.value,
            onValueChange = {
                autocomplete.value =it
                onValueChanged?.invoke(it)
                showSuggestions.value =true
            },
            label = label,
            maxLines = maxLines,
            enabled = enable,
            placeholder =hint,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),//.padding(10.dp),
            shape = shape,
            leadingIcon= leadingIcon,
            trailingIcon =trailingIcon,
            isError =isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(),
        )

        val autocompleteViewModel: CoreController<AutocompleteState> = provideAutoCompleteController()

        autocompleteViewModel.queryMapbox(autocomplete.value, searchProperty)

        AnimatedVisibility(visible = showSuggestions.value) {
            AutoCompleteContent(autocompleteViewModel.state, onError = onError,
                content = content, onSelectItem = { feature ->
                    onSelectItem?.invoke(feature)
                    showSuggestions.value = false
                }, showSuggestions = showSuggestions)
        }
    }

}

@Composable
fun AutoCompleteContent(stateFlow: StateFlow<AutocompleteState>,
                        showSuggestions: MutableState<Boolean>,
                        content: @Composable ((Suggestion) -> Unit)? =null,
                        onError: @Composable ((Throwable) -> Unit)? =null,
                        onSelectItem: ((Suggestion) -> Unit)? =null){
    //("AutoCompViewModel", ""+ vmState)

    val listHeight = rememberSaveable{
        mutableStateOf(0)
    }
    val vmState =stateFlow.collectAsState()


    val localDensity = LocalDensity.current

   val newDp =  with(localDensity){
        listHeight.value.toDp()
    }

    when(val state = vmState.value.place){
        is PlaceState.Success ->{
            LazyColumn(modifier =
            Modifier
                .shadow(elevation = 1.dp)
                .animateContentSize()
                .nestedScroll(rememberNestedScrollInteropConnection())
                .height(newDp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
            ) {
                val places = state.data.suggestions
                items(places?.size ?: 0) {
                    if (content == null){
                        PlaceItem(modifier =Modifier
                            .onGloballyPositioned {layoutCoord ->
                                                  listHeight.value = layoutCoord.size.height * (places?.size ?: 0)
                        },places?.get(it),
                            it == (places?.size ?:0)-1,
                            onSelectItem = onSelectItem)
                        return@items
                    }
                    places?.get(it)?.let {suggestion->
                        content.invoke(suggestion)
                    }
                }
            }
        }
        is PlaceState.Error ->{
            if(!showSuggestions.value){
                showSuggestions.value =false
            }
            onError?.invoke(state.error)
        }
        else ->{}
    }
}

@Composable
fun PlaceItem(modifier: Modifier, suggestion: Suggestion?, isLast: Boolean,
              onSelectItem: ((Suggestion) -> Unit)?){
    if (suggestion ==null)return;
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            onSelectItem?.invoke(suggestion)
        },
        verticalArrangement = Arrangement.Center
    ) {
        Text(suggestion.fullAddress ?: "", modifier = Modifier
            .padding(15.dp))
        if(!isLast){
            Divider()
        }
    }
}