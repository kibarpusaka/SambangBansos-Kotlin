package com.example.sambang.Dashboard.Master.Kecamatan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ResponKecamatan
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SharedPref.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KecamatanPresenter(val dataKecamatanView: DataKecamatanView) {
    private val kecamatan = mutableListOf<ModelKecamatanMaster>()

    fun showAllData(){
        dataKecamatanView.onSuccessDataKecamatan(kecamatan)
    }

    fun filterData(q : String){
        val filtered = kecamatan.filter { v -> v.nama!!.toLowerCase().contains(q.toLowerCase()) }
        dataKecamatanView.onSuccessDataKecamatan(filtered)
    }


    fun getDataKecamatan(token : String){
        SambangUtils.getservice()
            .getKecamatan(token = "Token ${token}")
            .enqueue(object : Callback<ResponKecamatan>{
                override fun onResponse(
                    call: Call<ResponKecamatan>,
                    response: Response<ResponKecamatan>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        kecamatan.clear()
                        kecamatan.addAll(body.data_desa)
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