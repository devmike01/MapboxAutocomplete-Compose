package io.devmike01.mapboxautocompletetextfield

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.devmike01.compose.models.SearchProperty
import io.devmike01.compose.models.SearchTypes
import io.devmike01.compose.textfield.AutoCompleteTextField
import io.devmike01.mapboxautocompletetextfield.ui.theme.MapboxAutoCompleteTextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapboxAutoCompleteTextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mapboxAC = remember {
                        mutableStateOf("")
                    }
                    Column(verticalArrangement = Arrangement.Top) {
                        AutoCompleteTextField(autocomplete = mapboxAC,
                            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                        onSelectItem = {
                            Toast.makeText(this@MainActivity,
                                it.fullAddress ?: "", Toast.LENGTH_LONG).show()
                            mapboxAC.value =""
                        }, searchProperty = SearchProperty(country = "gb",
                                searchTypes = SearchTypes.Place)
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MapboxAutoCompleteTextFieldTheme {
        Greeting("Android")
    }
}