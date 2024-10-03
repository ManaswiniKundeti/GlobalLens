package com.example.globallens.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.globallens.viewmodel.RestCountriesViewModel

@Composable
fun DisplayDetails(
    countryName: String,
    viewModel: RestCountriesViewModel,
    pressOnBack: () -> Unit = {}
) {

    // run suspend functions in the scope of a composable
    LaunchedEffect(key1 = countryName, block = {
        viewModel.getJsonCountries()
    })

    val jsonCountries by viewModel.jsonCountriesFlow.collectAsState(initial = null)

    Column {
        Box() {
            Text(" Details of $countryName")

        }
        jsonCountries?.let {
            LazyColumn {
                items(it) { country ->
                    Text("${country.name}, ${country.code}")
                }
            }
        }

    }

}