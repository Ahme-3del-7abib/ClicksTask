package com.example.clickstask.core.network.base

import com.example.clickstask.R
import com.example.clickstask.core.base.UserMessage
import com.example.clickstask.core.network.remote.APIResult
import com.example.clickstask.core.network.sharedModels.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response

open class BaseRemoteDataSource {

    fun <T : NetworkResponse> safeApiCall(
        call: suspend () -> Response<T>
    ): Flow<APIResult<T>> =
        flow {
            emit(safeApiResult(call))
        }

    private suspend fun <T : NetworkResponse> safeApiResult(call: suspend () -> Response<T>): APIResult<T> {
        var response: Response<T>? = null
        try {
            response = call.invoke()

        } catch (ex: Exception) {
            ex.printStackTrace()
            return getResultError(response)
        }

        if (response.isSuccessful) {
            return APIResult.Success(response.body())
        }

        return getResultError(response)
    }

    private fun <T> getResultError(response: Response<T>?): APIResult.Error {
        return when (response?.code()) {

            401 -> {
                APIResult.Error(ErrorTypes.AuthenticationError())
            }

            in 402..499 -> {
                val message: String = try {
                    val jObjError = JSONObject(response?.errorBody()?.string().orEmpty())
                    jObjError.getString("message").orEmpty()

                } catch (e: Exception) {
                    "Error happened, try again."
                }

                APIResult.Error(
                    ErrorTypes.NetworkError(
                        UserMessage(strMessage = message),
                        response?.code().toString()
                    )
                )
            }

            500 -> {
                APIResult.Error(
                    ErrorTypes.NetworkError(
                        UserMessage(R.string.general_error)
                    )
                )
            }

            else -> {
                APIResult.Error(
                    ErrorTypes.NetworkError(
                        UserMessage(strMessage = response?.errorBody()?.string().orEmpty()),
                        response?.code().toString()
                    )
                )
            }
        }
    }

    protected fun <T : NetworkResponse> getAPIResult(
        response: APIResult<*>,
        data: T?
    ): APIResult<T> {
        return when (response) {
            is APIResult.Success -> {
                return APIResult.Success(data!!)
            }
            is APIResult.Error -> {
                response
            }
        }
    }
}