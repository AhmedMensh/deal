package com.example.models

sealed class DataResult<out T> {
    class Success<T>(val date: T) : DataResult<T>()
    class Error<T>(val error: String) : DataResult<T>()
}