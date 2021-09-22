package com.example.clickstask.core.base

import android.content.Context
import com.example.clickstask.R

data class UserMessage(
    private val resMessage: Int? = null,
    private val strMessage: String? = null
) {
    fun getMessage(context: Context): String {
        return if (resMessage != null) context.getString(resMessage)
        else strMessage ?: context.getString(R.string.general_error)
    }
}