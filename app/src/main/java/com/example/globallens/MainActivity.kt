package com.example.globallens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.globallens.ui.compose.DisplayCountry
import com.example.globallens.ui.compose.MainScreen
import com.example.globallens.ui.theme.GlobalLensTheme
import com.example.globallens.viewmodel.RestCountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : RestCountriesViewModel by viewModels<RestCountriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCountriesByRegion("asia")
        setContent {
            GlobalLensTheme {
                MainScreen()
            }
        }
    }
}