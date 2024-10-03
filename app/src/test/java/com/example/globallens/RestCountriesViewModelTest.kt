package com.example.globallens

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.globallens.data.Country
import com.example.globallens.data.CountryCurrency
import com.example.globallens.data.CountryFlag
import com.example.globallens.data.CountryName
import com.example.globallens.data.NativeName
import com.example.globallens.repository.RestCountriesRepository
import com.example.globallens.viewmodel.RestCountriesViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class RestCountriesViewModelTest {

    // Rule to ensure LiveData works synchronously in tests
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // test dispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: RestCountriesViewModel
    private val repository: RestCountriesRepository = Mockito.mock()

    val mockCountriesList = listOf(
        Country(
            name = CountryName(
                common = "Germany",
                official = "Federal Republic of Germany",
                nativeName = mapOf(
                    "deu" to NativeName(official = "Bundesrepublik Deutschland", common = "Deutschland")
                )
            ),
            independent = true,
            currencies = mapOf(
                "EUR" to CountryCurrency(name = "Euro", symbol = "€")
            ),
            capital = listOf("Berlin"),
            region = "Europe",
            subRegion = "Western Europe",
            languages = mapOf("deu" to "German"),
            population = 83000000,
            flags = CountryFlag(
                png = "https://flagcdn.com/w320/de.png",
                svg = "https://flagcdn.com/de.svg",
                alt = "Flag of Germany"
            )
        ),
        Country(
            name = CountryName(
                common = "France",
                official = "French Republic",
                nativeName = mapOf(
                    "fra" to NativeName(official = "République française", common = "France")
                )
            ),
            independent = true,
            currencies = mapOf(
                "EUR" to CountryCurrency(name = "Euro", symbol = "€")
            ),
            capital = listOf("Paris"),
            region = "Europe",
            subRegion = "Western Europe",
            languages = mapOf("fra" to "French"),
            population = 67000000,
            flags = CountryFlag(
                png = "https://flagcdn.com/w320/fr.png",
                svg = "https://flagcdn.com/fr.svg",
                alt = "Flag of France"
            )
        )
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RestCountriesViewModel(repository)
    }

    @Test
    fun `getCountriesByRegion should update countriesFlow when data is returned`(): Unit = runTest {

        // open test coroutine scope
        kotlinx.coroutines.test.runTest {

            // Mock the repository call
            `when`(
                repository.getCountriesByRegion("Europe")
            ).thenReturn(mockCountriesList)

            // Trigger the ViewModel method to test
            viewModel.getCountriesByRegion("Europe")
        }

        // Advance the coroutine to capture the flow result
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert that the flow has emitted the expected data
        val result = viewModel.countriesFlow.value.first()
        assertEquals(mockCountriesList.first(), result)
    }



    @Test
    fun `getCountriesByRegion should not update countriesFlow when data is empty`() = runTest {
        val mockCountries = emptyList<Country>()

        // Mock the repository call
        `when`(repository.getCountriesByRegion("Europe"))
            .thenReturn(mockCountries)

        // Trigger the ViewModel method
        viewModel.getCountriesByRegion("Europe")

        // Advance the coroutine
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert that the flow remains unchanged (empty)
        val result = viewModel.countriesFlow.value
        assertTrue(result.isEmpty())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}