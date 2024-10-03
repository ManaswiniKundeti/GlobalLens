package com.example.globallens

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.globallens.ui.compose.DisplayCountry
import com.example.globallens.ui.compose.MainScreen
import com.example.globallens.viewmodel.RestCountriesViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class DisplayCountryTest {

    // Sets up Hilt dependency injection for the test
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    // Compose testing rule to test MainActivity
    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()

        composeTestRule.activity.setContent {
            MainScreen()
//            DisplayCountry(composeTestRule.activity.viewModels<RestCountriesViewModel>().value)
        }
    }

    @Test
    fun testDisplayCountry_ShowsCountries() {
        composeTestRule.onNodeWithTag("countryList").assertIsDisplayed()
         //Assert that countries are displayed
        composeTestRule.onNodeWithText("Germany").assertExists()
        composeTestRule.onNodeWithText("France").assertExists()
        composeTestRule.onNodeWithText("China").assertDoesNotExist()
    }


}