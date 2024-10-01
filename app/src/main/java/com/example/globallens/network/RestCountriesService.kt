package com.example.globallens.network

import com.example.globallens.data.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesService {

    @GET("v3.1/region/{region}")
    suspend fun getCountryByRegion(
        @Path("region") region: String
    ): List<Country>

}