package com.example.sambang.ui.common.extension

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

fun Context.makeToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.makeAlert(message: String){
    AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton("OK"){d , _ ->
            d.cancel()
        }
    }.show()
}