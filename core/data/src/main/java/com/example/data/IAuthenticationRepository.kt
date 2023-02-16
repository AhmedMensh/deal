package com.example.data

import com.example.model.LoginResponse
import com.example.model.NetworkResource

interface IAuthenticationRepository {

    fun requestLogin(userName: String, password: String): NetworkResource<LoginResponse>
}