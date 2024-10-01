package com.example.globallens.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.globallens.data.Country
import com.example.globallens.viewmodel.RestCountriesViewModel
import java.util.Locale

@Composable
fun DisplayCountry(viewModel: RestCountriesViewModel) {

    val countriesList: List<Country> = viewModel.countryLiveData.observeAsState().value ?: emptyList()

    Column {
        Box(modifier = Modifier.padding(10.dp)) {
            Text("Countries in Asia".capitalize(Locale.ROOT), style = MaterialTheme.typography.headlineLarge, color = Color.Red)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn {
            items(countriesList) { country ->
                CountryItem(country)
            }
        }
    }

}

@Composable
fun CountryItem(country: Country) {
    Box(modifier = Modifier.padding(5.dp)) {
        Text(text = country.name.common, modifier = androidx.compose.ui.Modifier.align(Alignment.Center))
    }

}
