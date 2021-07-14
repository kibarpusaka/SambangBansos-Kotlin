package com.example.sambang.Login

import com.example.sambang.Login.Data.ModelLogin

interface LoginView {

    fun onSuccessLogin(token: String, user: ModelLogin)
    fun onErrorLogin(msg: String?)
}