package com.example.globallens.di

import android.content.Context
import com.example.globallens.jsonSample.service.JsonCountryService
import com.example.globallens.network.RestCountriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRestCountriesService(retrofit: Retrofit): RestCountriesService {
        return retrofit.create(RestCountriesService::class.java)
    }

    @Provides
    @Singleton
    fun provideJsonCountriesService(@ApplicationContext context: Context): JsonCountryService {
        return JsonCountryService(context)
    }
}