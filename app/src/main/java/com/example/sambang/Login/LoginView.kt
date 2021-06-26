package com.example.sambang.Login

import com.example.sambang.Login.Data.ModelLogin

interface LoginView {

    fun onSuccessLogin(user: ModelLogin?)
    fun onErrorLogin(msg: String?)
}