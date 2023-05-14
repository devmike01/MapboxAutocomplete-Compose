# MapboxAutocomplete - Compose
### Description
<br/>
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/devmike01/MapboxAutocomplete-Compose/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/devmike01/MapboxAutocomplete-Compose/tree/master)
<br/>
Place auto complete for Jetpack compose backed by the Mapbox API.
### Screenshot
<img src="/assets/Screenshot_20230505_173705.png" 
width="250px" height="500px"/>
### Implementation
- Add `mapboxautocomplete` to your `.gradle` or `.gradle.kts`file
- - Step 1
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
- - Step 2
```groovy
dependencies {
	        implementation 'com.github.devmike01:MapboxAutocomplete-Compose:Faling'
	}
```
- Import the library where you want to use it
- Add your mapbox API KEY to your `local.properties` file.
- Add the below code in your `composable` function.
And you're good to go.
```kotlin
  val mapboxValue = remember {
    mutableStateOf("")
}
AutoCompleteTextField(autocomplete = mapboxValue,
    modifier = Modifier.wrapContentHeight().fillMaxWidth(),
    onSelectItem = {
        Toast.makeText(this@MainActivity,
            it.fullAddress ?: "", Toast.LENGTH_LONG).show()
        mapboxAC.value =""
    }, searchProperty = SearchProperty(country = "gb",
        searchTypes = SearchTypes.Place)
)
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