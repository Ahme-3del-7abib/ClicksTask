package com.example.clickstask.core.network.base

import com.example.clickstask.R
import com.example.clickstask.core.base.UserMessage

sealed class ErrorTypes(open val errorMessage: UserMessage?) {

    class ConnectError(override val errorMessage: UserMessage? = UserMessage(resMessage = R.string.check_internet)) :
        ErrorTypes(errorMessage)

    class AuthenticationError(override val errorMessage: UserMessage? = UserMessage(resMessage = R.string.authentication_error)) :
        ErrorTypes(errorMessage)

    class NoData(override val errorMessage: UserMessage? = UserMessage(resMessage = R.string.no_data)) :
        ErrorTypes(errorMessage)

    class GeneralError(override val errorMessage: UserMessage, val statusCode: String? = null) :
        ErrorTypes(errorMessage)

    class NetworkError(override val errorMessage: UserMessage, val statusCode: String? = null) :
        ErrorTypes(errorMessage)
}