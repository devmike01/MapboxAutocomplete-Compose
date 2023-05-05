# MapboxAutocomplete - Compose
### Description
Place auto complete for Jetpack compose backed by the Mapbox API.
### Screenshot
<img src="/assets/Screenshot_20230505_173705.png" 
width="250px" height="500px"/>
### Implementation
- Add `mapboxautocomplete` to your `.gradle` or `.gradle.kts`file
- Import the library where you want to use it
- Add the below code in your `composable` function.
And you're good to go.
```kotlin
  val mapboxValue = remember {
    mutableStateOf("")
}
AutoCompleteTextField(autocomplete = mapboxValue,
                            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                        onSelectItem = {
                            Toast.makeText(this@MainActivity, it.placeName ?: "", Toast.LENGTH_LONG).show()
                        })
```
### License
```
Copyright [yyyy] [name of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.```