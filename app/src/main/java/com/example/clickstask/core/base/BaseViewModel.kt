package com.example.clickstask.core.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clickstask.core.network.base.ErrorTypes
import com.example.clickstask.core.utils.showMessage

open class BaseViewModel : ViewModel() {

    protected val _showErrorMessage = MutableLiveData<UserMessage>()

    fun handleErrorTypes(errorTypes: ErrorTypes) {
        when (errorTypes) {
            is ErrorTypes.AuthenticationError -> _showErrorMessage.value = errorTypes.errorMessage
            is ErrorTypes.ConnectError -> _showErrorMessage.value = errorTypes.errorMessage
            is ErrorTypes.GeneralError -> _showErrorMessage.value = errorTypes.errorMessage
            is ErrorTypes.NetworkError -> _showErrorMessage.value = errorTypes.errorMessage
            is ErrorTypes.NoData -> _showErrorMessage.value = errorTypes.errorMessage
        }
    }

    fun showErrorMessage(requireContext: Context, errorMessage: UserMessage?) {
        requireContext.showMessage(error = errorMessage?.getMessage(requireContext).orEmpty())
    }
}
