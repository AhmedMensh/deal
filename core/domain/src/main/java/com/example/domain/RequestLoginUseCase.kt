package com.example.domain

import com.example.data.IAuthenticationRepository
import com.example.models.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RequestLoginUseCase @Inject constructor(
    private val iAuthenticationRepository: IAuthenticationRepository
) {

    operator fun invoke(userName: String, password: String) =
        flow {
            val response = iAuthenticationRepository.requestLogin(userName, password)

            when {
                response.isSuccess() -> emit(DataResult.Success("Success Login"))
                response.isFailure() -> emit(DataResult.Error("Something went wrong"))
            }
        }.flowOn(Dispatchers.IO)
}