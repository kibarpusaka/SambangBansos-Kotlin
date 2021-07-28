package com.example.sambang.Login.Presenter

import com.example.sambang.Login.Data.ResponLogin
import com.example.sambang.Login.LoginView
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Login.Data.ModelLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val loginView: LoginView) {

    fun login(user : ModelLogin){
        SambangUtils.getservice()
            .loginUser(user.username, user.password)
            .enqueue(object : Callback<ResponLogin>{
                override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                    val body = response.body()
                    if (body?.status == true){
                        loginView.onSuccessLogin(body.token, body.user)
                    }else {
                        loginView.onErrorLogin(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponLogin>, t: Throwable) {
                    loginView.onErrorLogin(t.localizedMessage)
                }

            })

    }
}