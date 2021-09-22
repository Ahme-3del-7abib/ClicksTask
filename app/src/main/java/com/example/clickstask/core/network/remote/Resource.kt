package com.example.clickstask.core.network.remote

import com.example.clickstask.core.network.base.ErrorTypes

sealed class Resource<T>(val data: T? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(val show: Boolean) : Resource<T>()
    class Error<T>(val errorTypes: ErrorTypes) : Resource<T>()
}