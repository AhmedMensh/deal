package com.example.di

import com.example.data.AuthenticationRepositoryImpl
import com.example.data.IAuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindAuthenticationRepositoryImpl(
        mAuthenticationRepository: AuthenticationRepositoryImpl
    ): IAuthenticationRepository

}