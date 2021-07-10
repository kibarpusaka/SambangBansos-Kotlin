package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ResponDaftarUsulan
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarUsulanPresenter(val dataDaftarUsulanView: DaftarUsulanView) {
    fun getDataDaftarUsulan(user: ModelLogin?){
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getDaftarUsulan(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponDaftarUsulan>{
                override fun onResponse(
                    call: Call<ResponDaftarUsulan>,
                    response: Response<ResponDaftarUsulan>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataDaftarUsulanView.onSuccessDataDaftarUsulan(body.data_warga)
                    }else{
                        dataDaftarUsulanView.onErrorDataDaftarUsulan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponDaftarUsulan>, t: Throwable) {
                    dataDaftarUsulanView.onErrorDataDaftarUsulan(t.localizedMessage)
                }

            })
    }
}