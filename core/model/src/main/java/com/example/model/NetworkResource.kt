package com.example.model

import android.util.Log
import okhttp3.ResponseBody


class NetworkResource<T> private constructor(
    private val successValue: T? = null,
    private val failureValue: NetworkFailure? = null
) {
    fun isSuccess(): Boolean = failureValue == null
    fun isFailure(): Boolean = failureValue != null

    fun successValue(): T? = successValue
    fun failureValue(): NetworkFailure? = failureValue

    fun onSuccessWithValue(action: (successValue: T?) -> Unit): NetworkResource<T> {
        if (isSuccess()) successValue?.let { action.invoke(it) }
        return this
    }

    fun onFailureWithValue(action: (failureValue: NetworkFailure?) -> Unit): NetworkResource<T> {
        if (isSuccess()) failureValue?.let { action.invoke(it) }
        return this
    }

    companion object {
        fun success(): NetworkResource<Nothing> = NetworkResource()
        fun <T> success(value: T): NetworkResource<T> = NetworkResource(successValue = value)
        fun <T> failure(value: NetworkFailure): NetworkResource<T> =
            NetworkResource(failureValue = value)
    }
}

data class NetworkFailure(
    val message: String? = null,
    val exception: Exception = ResourceException()
) {
    init {
        logError(exception)
    }
}

fun <T> tryCatchNetworkRequest(block: () -> NetworkResource<T>): NetworkResource<T> = try {
    block()
} catch (exception: ApiErrorException) {
    NetworkResource.failure(NetworkFailure(message = "Api Error Exception", exception = exception))
} catch (e: Exception) {
    NetworkResource.failure(NetworkFailure(message = e.message, exception = e))
}

fun logError(exception: Exception) {
    val message = when (exception) {
        is ResourceException,
        is ResultUnSuccessfulException,
        is NoInternetException,
        is RefreshTokenExpiredException -> exception.message
        is ApiErrorException -> "${exception.message}  ->> ${exception.response.string()}"
        else -> "UNKNOWN NETWORK ERROR"
    }
    Log.d("","tryCatchNetworkRequest: $message")
}

class ResourceException : Exception()
class ApiErrorException(val response: ResponseBody) : Exception()
class ResultUnSuccessfulException : Exception()
class NoInternetException(override val message:String) : Exception()
class RefreshTokenExpiredException : Exception()