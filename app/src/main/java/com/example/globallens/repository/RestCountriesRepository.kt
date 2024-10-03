package com.example.globallens.repository

import com.example.globallens.data.Country
import com.example.globallens.jsonSample.data.JsonCountry
import com.example.globallens.jsonSample.service.JsonCountryService
import com.example.globallens.network.RestCountriesService
import javax.inject.Inject

class RestCountriesRepository @Inject constructor(
    private val service: RestCountriesService,
    private val jsonCountryService: JsonCountryService
) {

    suspend fun getCountriesByRegion(region: String): List<Country> {
        return try {
            // API Call
            val response = service.getCountryByRegion(region)

            // check is message is not empty & meaningful
            if (response.isNotEmpty()) {
                response
            } else {
                throw Exception("No countries found for the given region\"")
            }
        } catch (e: Exception) {
            // Log or handle the exception
            throw Exception("Failed to fetch countries. Please try again later.")
        }
    }

   suspend fun getCountries(): List<JsonCountry> {
        return jsonCountryService.getCountries()
    }
}