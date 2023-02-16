package com.example.data

import com.example.model.LoginResponse
import com.example.model.NetworkResource
import com.example.model.tryCatchNetworkRequest
import com.example.network.DealRemoteDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val dealRemoteDataSource: DealRemoteDataSource
) : IAuthenticationRepository {
    override fun requestLogin(userName: String, password: String): NetworkResource<LoginResponse> =
        tryCatchNetworkRequest {
            val response = dealRemoteDataSource.requestLogin(userName, password)
            NetworkResource.success(response)
        }
}