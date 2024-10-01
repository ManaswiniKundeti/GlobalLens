package com.example.globallens.data

import android.icu.util.Currency
import com.google.gson.annotations.SerializedName

data class Country(
    val name: CountryName,
    val independent: Boolean = true,
    val currencies: Map<String, CountryCurrency>,
    val capital: List<String>,
    val region: String,
    val subRegion : String,
    val languages: Map<String, String>,
    val population: Int,
    val flags: CountryFlag,
)

data class CountryName(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>
)

data class NativeName(
    val official: String,
    val common: String
)


data class CountryCurrency(
    val name: String,
    val symbol: String
)

data class CountryFlag(
    val png: String,
    val svg: String,
    val alt: String
)