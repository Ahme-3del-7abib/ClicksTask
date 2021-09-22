package com.example.clickstask.core.network.remote

import com.example.clickstask.core.network.base.ErrorTypes

sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : APIResult<T>()
    data class Error(val errorTypes: ErrorTypes) : APIResult<Nothing>()
}