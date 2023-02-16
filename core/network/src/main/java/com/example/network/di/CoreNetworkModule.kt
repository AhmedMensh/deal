package com.example.network.di

import com.example.mazaady.data.network.interceptors.ApiInterceptor
import com.example.network.BuildConfig
import com.example.network.DealRemoteDataSource
import com.example.network.DealRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {


    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()


        builder.apply {
            addInterceptor(ApiInterceptor())
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
            }
        }
        return builder.connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitServices(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideNetworkFailureAdapter(): JsonAdapter<NetworkFailure> {
//        val moshi: Moshi = Moshi.Builder().build()
//        return moshi.adapter(NetworkFailure::class.java)
//    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindRemoteDataSourceImpl(
        dealRemoteDataSourceImpl: DealRemoteDataSourceImpl
    ): DealRemoteDataSource

}