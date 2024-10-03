package com.example.globallens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globallens.data.Country
import com.example.globallens.repository.RestCountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestCountriesViewModel @Inject constructor(
    private val repository: RestCountriesRepository
): ViewModel() {

    private val _countryLiveData = MutableLiveData<List<Country>>()
    val countryLiveData: LiveData<List<Country>> = _countryLiveData

    private val _countriesFlow = MutableStateFlow<List<Country>>(emptyList())
    val countriesFlow = _countriesFlow.asStateFlow()


    fun getCountriesByRegion(region: String) {
        viewModelScope.launch {
           val countriesList = repository.getCountriesByRegion(region)
            if (countriesList.isNotEmpty()) {
                _countriesFlow.value = countriesList
            }
        }
    }
}