package com.example.network

import com.example.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface DealApiService {
    @FormUrlEncoded
    @POST("/api/login")
    fun requestLogin(
        @Field("username") userName: String,
        @Field("password") password: String
    ): Call<LoginResponse>



}
