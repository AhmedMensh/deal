package com.example.network

import com.example.model.LoginResponse
import javax.inject.Inject

class DealRemoteDataSourceImpl @Inject() constructor(
    private val dealApiService: DealApiService
) : DealRemoteDataSource {
    override fun requestLogin(userName: String, password: String): LoginResponse {
        return dealApiService.requestLogin(userName, password).execute().body()!!
    }
}