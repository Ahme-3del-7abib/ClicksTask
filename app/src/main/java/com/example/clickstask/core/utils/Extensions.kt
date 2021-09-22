package com.example.clickstask.core.utils

import android.app.AlertDialog
import android.content.Context
import com.example.clickstask.R


fun Context.showMessage(error: String) {
    AlertDialog.Builder(this).setTitle(getString(R.string.app_name))
        .setMessage(error)
        .setPositiveButton(
            getString(R.string.ok)
        ) { _, _ ->
            // No Action
        }
        .setCancelable(false)
        .create()
        .show()
}
