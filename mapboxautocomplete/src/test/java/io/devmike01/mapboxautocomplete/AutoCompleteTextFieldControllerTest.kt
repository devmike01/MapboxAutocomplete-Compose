package io.devmike01.mapboxautocomplete

import io.devmike01.mapboxautocomplete.models.Feature
import io.devmike01.mapboxautocomplete.models.Place
import io.devmike01.mapboxautocomplete.models.SearchProperty
import io.devmike01.mapboxautocomplete.models.SearchTypes
import io.devmike01.mapboxautocomplete.repo.MapboxService
import io.devmike01.mapboxautocomplete.textfield.AutoCompleteTextFieldController
import io.devmike01.mapboxautocomplete.repo.AutoCompleteTextFieldRepo
import io.devmike01.mapboxautocomplete.repo.Suggestion
import io.devmike01.mapboxautocomplete.repo.SuggestionResponse
import io.devmike01.mapboxautocomplete.textfield.AutocompleteState
import io.devmike01.mapboxautocomplete.textfield.CoreController
import io.devmike01.mapboxautocomplete.textfield.PlaceState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class AutoCompleteTextFieldControllerTest {

    private var controller : CoreController<AutocompleteState>? = null
    private var autoCompleteRepo : AutoCompleteTextFieldRepo? = null
    private val mapService : MapboxService = spy(MapboxServiceFake::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init(){
       autoCompleteRepo = AutoCompleteTextFieldRepo(service = mapService)
        controller =AutoCompleteTextFieldController(
            autocompleteRepo = autoCompleteRepo!!,
            coroutineContext = UnconfinedTestDispatcher())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected=Exception::class)
    fun `test query map box failed`(){
        runTest {
            `when`(autoCompleteRepo?.queryMapbox("-",
                searchProperty = SearchProperty()
            ))
                .thenThrow(Exception("An error has occurred"))
            controller?.queryMapbox("-",
                searchProperty = SearchProperty())
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
//            `when`(getSuggestion())
//                .thenReturn(
//                    SuggestionResponse(suggestions =
//                    listOf(Suggestion(fullAddress =
//                    "3 Oshodi road, Apapa, Lagos state, Nigeria")))
//                )
            controller?.queryMapbox("Hello",
                searchProperty = SearchProperty(searchTypes = SearchTypes.Address))
            verify(mapService, atLeastOnce())?.getPlace(  "Hello",
                "en",
                10,
                "",
                "",
                "",
                "",
                "address",
                "",
                "")
            delay(2000)
            val exactValue = controller?.state?.value
            assertThat(exactValue?.place, instanceOf( PlaceState.Success(data = SuggestionResponse())::class.java))

            println("NEWVALUE => ${exactValue?.place}")
            val result = (exactValue?.place as PlaceState.Success)
            assertTrue(result.data.suggestions?.isNotEmpty() ==true)
            println("NEWVALUE => ${result.data.suggestions?.size}")
            assertEquals(result.data.suggestions?.first()?.fullAddress, "5 Oshodi road, Apapa, Lagos state, Nigeria")
            assertEquals(result.data.suggestions?.last()?.fullAddress, "Ikeja Shopping Mall")
        }
    }


    private suspend fun getSuggestion(): SuggestionResponse{
        return mapService.getPlace("Hello", "", 10, "", "", "", "", "", "", "")
    }
    @After
    fun tearDown(){
        controller = null
    }
}