package com.example.globallens

import com.example.globallens.data.Country
import com.example.globallens.data.CountryCurrency
import com.example.globallens.data.CountryFlag
import com.example.globallens.data.CountryName
import com.example.globallens.data.NativeName
import com.example.globallens.network.RestCountriesService

class FakeRestCountriesService: RestCountriesService {
    override suspend fun getCountryByRegion(region: String): List<Country> {
        return listOf(
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
    }
}