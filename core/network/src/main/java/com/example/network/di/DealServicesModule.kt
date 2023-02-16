package com.example.network.di

import com.example.network.DealApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DealServicesModule {
    @Singleton
    @Provides
    fun provideDealApiService(retrofit: Retrofit) =
        retrofit.create(DealApiService::class.java)


}