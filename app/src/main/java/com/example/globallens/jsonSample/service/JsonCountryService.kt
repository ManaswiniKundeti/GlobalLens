package com.example.globallens.jsonSample.service

import android.content.Context
import com.example.globallens.jsonSample.data.JsonCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class JsonCountryService(private val context: Context) {

    // load json from assets
    private fun loadJsonFromAsset(): String? {
        return try {
            val inputStream = context.assets.open("sample.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }



    // parse json and return list of countries
    fun getCountries(): List<JsonCountry> {
        val json = loadJsonFromAsset() ?: return emptyList()

        //  Parse the JSON using Gson
        val gson = Gson()
        val countryListType = object : TypeToken<List<JsonCountry>>() {}.type
        return gson.fromJson(json, countryListType)
    }
}