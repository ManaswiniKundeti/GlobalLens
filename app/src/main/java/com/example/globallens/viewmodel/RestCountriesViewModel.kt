package com.example.globallens.viewmodel

import android.graphics.Region
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globallens.data.Country
import com.example.globallens.repository.RestCountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestCountriesViewModel @Inject constructor(
    private val repository: RestCountriesRepository
): ViewModel() {

    private val _countryLiveData = MutableLiveData<List<Country>>()
    val countryLiveData: LiveData<List<Country>> = _countryLiveData

    fun getCountriesByRegion(region: String) {
        viewModelScope.launch {
           val countriesList = repository.getCountriesByRegion(region)
            if (countriesList.isNotEmpty()) {
//                Log.d("TAG123", countriesList.toString())
              _countryLiveData.value = countriesList
            }
        }
    }
}