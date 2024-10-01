package com.example.globallens

import com.example.globallens.data.Country
import com.example.globallens.data.CountryCurrency
import com.example.globallens.data.CountryFlag
import com.example.globallens.data.CountryName
import com.example.globallens.data.NativeName
import com.example.globallens.network.RestCountriesService
import com.example.globallens.repository.RestCountriesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class RestCountriesRepositoryTest {

    private lateinit var repository: RestCountriesRepository

    private lateinit var service: RestCountriesService

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher  = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        service = Mockito.mock(RestCountriesService::class.java)
        repository = RestCountriesRepository(service)
    }

    @Test
    fun `getCountriesByRegion returns list of countries when service is successful`() = runTest(testDispatcher) {
        val region = "europe"
// Mock data for a list of countries
        val countriesList = listOf(
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

        // mock call
        `when`(service.getCountryByRegion(region)).thenReturn(countriesList)

        // action call
       val result =  repository.getCountriesByRegion(region)

        // assert
        assertEquals(countriesList, result)
        verify(service).getCountryByRegion(region)


    }

    @Test
    fun `getCountriesByRegion returns emptyList when service returns empty list`() = runTest(testDispatcher) {
        val region = "europe"
        val countriesList = emptyList<Country>()

        // mock service call to return empty list
        `when`(service.getCountryByRegion(region)).thenReturn(countriesList)

        // When call
        val result = repository.getCountriesByRegion(region)

        //then assert
        assertEquals(countriesList, result)
        verify(service).getCountryByRegion(region)


    }

}