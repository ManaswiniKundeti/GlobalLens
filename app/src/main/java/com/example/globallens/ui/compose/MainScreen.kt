package com.example.globallens.ui.compose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.globallens.viewmodel.RestCountriesViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var viewModel: RestCountriesViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ScreenNavigator.Home.route) {

       composable(route = ScreenNavigator.Home.route) {
           // display UI for HomeScreen Route
           DisplayCountry(
               viewModel = viewModel,
               selectCountry = { countryName ->
                    navController.navigate(route = "${ScreenNavigator.Details.route}/${countryName}")
               }
           )
       }
        composable(
            route = ScreenNavigator.Details.routeWithArgument,
            arguments = listOf(navArgument(ScreenNavigator.Details.argument0) { type = NavType.StringType } )
        ) {
            // display UI for Details Route
            val selectedCountryName = it.arguments?.getString(ScreenNavigator.Details.argument0) ?: return@composable

            DisplayDetails(selectedCountryName, viewModel) {
                navController.navigateUp()
            }
        }
    }
}


// controlled inheritance: a class that restricts which other classes can inherit from it
sealed class ScreenNavigator(val route: String) {
    object Home: ScreenNavigator("Home")
    object Details: ScreenNavigator("Details") {
        const val routeWithArgument: String = "Details/{countryName}"
        const val argument0: String = "countryName"
    }
}