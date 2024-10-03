package com.example.globallens.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.globallens.data.Country
import com.example.globallens.viewmodel.RestCountriesViewModel
import java.util.Locale

@Composable
fun DisplayCountry(
    viewModel: RestCountriesViewModel,
    selectCountry: (String) -> Unit
) {

    val countriesList: List<Country> = viewModel.countryLiveData.observeAsState().value ?: emptyList()

    val countriesFlow: List<Country> by viewModel.countriesFlow.collectAsState()
    Column {
        Box(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Countries in Asia".uppercase(Locale.US),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn(modifier = Modifier.testTag("countryList")) {
            items(countriesFlow) { country ->
                CountryItem(country, selectCountry)
            }
        }
    }

}

@Composable
fun CountryItem(country: Country, selectCountry: (String) -> Unit) {
    Box(modifier = Modifier.padding(5.dp).clickable { selectCountry(country.name.common)  }) {
        Row {
            Text(text = country.name.common.uppercase(Locale.US))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = " ----> ")
            country.currencies.forEach {
                Text(text = it.value.name)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = " ---> ")
            AsyncImage(
                model = country.flags.png,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .width(20.dp)
                    .height(20.dp),
                onError = { }
            )

        }
    }

}
