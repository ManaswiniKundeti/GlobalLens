package com.example.globallens.repository

import android.util.Log
import com.example.globallens.data.Country
import com.example.globallens.network.RestCountriesService
import javax.inject.Inject

class RestCountriesRepository @Inject constructor(
    private val service: RestCountriesService
) {

    suspend fun getCountriesByRegion(region: String): List<Country> {
        return service.getCountryByRegion(region)
    }
}