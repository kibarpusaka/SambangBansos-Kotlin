package com.example.sambang.Dashboard.Master.Bantuan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Master.Bantuan.Data.ResponBantuanMaster
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SharedPref.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BantuanMasterPresenter(val dataBantuanView: DataBantuanView) {
    fun getBantuan(token : String){
        SambangUtils.getservice()
            .getBantuan(token = "Token ${token}")
            .enqueue(object : Callback<ResponBantuanMaster>{
                override fun onResponse(
                    call: Call<ResponBantuanMaster>,
                    response: Response<ResponBantuanMaster>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataBantuanView.onSuccessDataBantuan(body.data)
                    }else{
                        dataBantuanView.onErrorDataBantuan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponBantuanMaster>, t: Throwable) {
                    dataBantuanView.onErrorDataBantuan(t.localizedMessage)
                }

            })
    }
}