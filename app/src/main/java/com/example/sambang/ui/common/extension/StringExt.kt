package com.example.sambang.ui.common.extension

import android.util.Patterns


fun String.isEmail(s: String) : Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}