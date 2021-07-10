package com.example.sambang.Dashboard.Master.Kecamatan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ResponKecamatan
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KecamatanPresenter(val dataKecamatanView: DataKecamatanView) {

    fun getDataKecamatan(user: ModelLogin?){
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getKecamatan(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponKecamatan>{
                override fun onResponse(
                    call: Call<ResponKecamatan>,
                    response: Response<ResponKecamatan>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataKecamatanView.onSuccessDataKecamatan(body.data_desa)
                    } else {
                        dataKecamatanView.onErrorDataKecamatan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponKecamatan>, t: Throwable) {
                    dataKecamatanView.onErrorDataKecamatan(t.localizedMessage)
                }

            })
    }
}