package com.eggroup.composearch.di

import com.eggroup.composearch.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .run {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(ApiService.BASE_URL)
                build()
            }.create(ApiService::class.java)

}