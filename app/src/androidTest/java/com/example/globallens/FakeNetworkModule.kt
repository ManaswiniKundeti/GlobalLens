package com.example.globallens

import com.example.globallens.di.NetworkModule
import com.example.globallens.network.RestCountriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object FakeNetworkModule {
    @Singleton
    @Provides
    fun provideRestCountriesService(): RestCountriesService {
        return FakeRestCountriesService()
    }
}