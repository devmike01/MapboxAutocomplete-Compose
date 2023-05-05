package io.devmike01.mapboxautocomplete

import io.devmike01.mapboxautocomplete.di.provideAutoCompleteController
import io.devmike01.mapboxautocomplete.models.Feature
import io.devmike01.mapboxautocomplete.models.Place
import io.devmike01.mapboxautocomplete.repo.MapboxService
import io.devmike01.mapboxautocomplete.textfield.AutoCompleteTextFieldController
import io.devmike01.mapboxautocomplete.textfield.AutoCompleteTextFieldRepo
import io.devmike01.mapboxautocomplete.textfield.AutocompleteState
import io.devmike01.mapboxautocomplete.textfield.PlaceState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.hamcrest.CoreMatchers.instanceOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class AutoCompleteTextFieldControllerTest {

    var controller : AutoCompleteTextFieldController? = null

    val mapService : MapboxService = mock(MapboxService::class.java)

    @Before
    fun init(){
        val autoCompleteRepo = AutoCompleteTextFieldRepo(service = mapService)
        @OptIn(ExperimentalCoroutinesApi::class)
        controller =AutoCompleteTextFieldController(autocompleteRepo = autoCompleteRepo,
            coroutineContext = UnconfinedTestDispatcher())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected=Exception::class)
    fun `test query map box failed`(){

        //assertTrue(controller?.state?.value?.place is PlaceState.Nothing)
        runTest {
            `when`(mapService.getPlaceByName("-"))
                .thenThrow(Exception("An error has occurred"))
            controller?.queryMapbox("-")
            val exactValue = controller?.state?.value
            assertTrue(exactValue?.place is PlaceState.Error)
            val errorResult = (exactValue?.place as PlaceState.Error)
            assertEquals(errorResult.error.message, "The API has failed")
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test query map box success`(){
        assertTrue(controller?.state?.value?.place is PlaceState.Nothing)
        runBlocking {
            `when`(mapService.getPlaceByName("Hello"))
                .thenReturn(Place(placeName = "Oshodi",
                    features = listOf(Feature())))
        }
        controller?.queryMapbox("Hello")
        runTest {
            val exactValue = controller?.state?.value
            assertThat(exactValue?.place, instanceOf( PlaceState.Success(data = Place())::class.java))
            val result = (exactValue?.place as PlaceState.Success)
            assertEquals(result.data.placeName, "Oshodi")
            assertTrue(result.data.features.isNotEmpty())
        }

    }


    @After
    fun tearDown(){
        controller = null
    }
}