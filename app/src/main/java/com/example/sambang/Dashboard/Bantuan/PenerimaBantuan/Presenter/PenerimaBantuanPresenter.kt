package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ResponPenerimaBantuan
import com.example.sambang.Dashboard.Report.Data.ModelReportPenerima
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SessionManager
import com.example.sambang.Utils.ResultSimple
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PenerimaBantuanPresenter(val dataPenerimaBantuanView: DataPenerimaBantuanView) {
    fun getDataPenerimaBantuan(user: ModelLogin?){
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getPenerimaBantuan(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponPenerimaBantuan>{
                override fun onResponse(
                    call: Call<ResponPenerimaBantuan>,
                    response: Response<ResponPenerimaBantuan>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataPenerimaBantuanView.onSuccessDataPenerimaBantuan(body.data_penerima_bantuan)
                    }else{
                        dataPenerimaBantuanView.onErrorDataPenerimaBantuan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponPenerimaBantuan>, t: Throwable) {
                    dataPenerimaBantuanView.onErrorDataPenerimaBantuan(t.localizedMessage)
                }

            })
    }

    fun deletePenerimaBantuan(user: ModelLogin?, penerima: ModelPenerimaBantuan){
        SambangUtils.getservice()
            .deletePenerimaBantuan(penerima.id)
            .enqueue(object : Callback<ResultSimple>{
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataPenerimaBantuanView.onSuccessDeletePenerimaBantuan(body.message)
                    }else{
                        dataPenerimaBantuanView.onErrorDeletePenerimaBantuan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable) {
                    dataPenerimaBantuanView.onErrorDeletePenerimaBantuan(t.localizedMessage)
                }

            })

    }
}