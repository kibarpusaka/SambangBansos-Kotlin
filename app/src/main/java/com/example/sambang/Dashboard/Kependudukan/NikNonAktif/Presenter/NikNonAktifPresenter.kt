package com.example.sambang.Dashboard.Kependudukan.NikNonAktif.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NikNonAktifPresenter(val dataNikAktifView: DataNikAktifView) {

    fun getNikNonAktif(user: ModelLogin?){
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getNikNonAktif(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponNikAktif>{
                override fun onResponse(
                    call: Call<ResponNikAktif>,
                    response: Response<ResponNikAktif>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        val filter = body.data_warga?.filter { w -> w.nikvalid == false }
                        dataNikAktifView.onSuccessDataNikAktif(filter)
                    } else {
                        dataNikAktifView.onErrorDataNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponNikAktif>, t: Throwable) {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
                }

            })
    }
}