package com.example.sambang.Dashboard.Master.Desa.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DesaPresenter(val dataDesaView: DataDesaView) {
    private val villages = mutableListOf<ModelDesaMaster>()

    fun showAllData(){
        dataDesaView.onSuccessDataDesa(villages)
    }

    fun filterData(q: String){
        val filtered = villages.filter { v -> v.nama!!.toLowerCase().contains(q.toLowerCase()) }
        dataDesaView.onSuccessDataDesa(filtered)
    }

    fun getDataDesa(user : ModelLogin?){
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getDesa(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponDesa>{
                override fun onResponse(call: Call<ResponDesa>, response: Response<ResponDesa>) {
                    val body = response.body()
                    if (body?.status == true) {
                        villages.clear()
                        villages.addAll(body.data_desa)
                        dataDesaView.onSuccessDataDesa(body.data_desa)
                    }else {
                        dataDesaView.onErrorDataDesa(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    dataDesaView.onErrorDataDesa(t.localizedMessage)
                }

            })

    }
}