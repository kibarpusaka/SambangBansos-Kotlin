package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.AddPenerimaBantuanView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Utils.ResultSimple
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPenerimaBantuanPresenter(val addPenerimaBantuanView: AddPenerimaBantuanView) {

    fun addPenerimaBantuan(penerima: ModelPenerimaBantuan){
        SambangUtils.getservice()
            .addPenerimaBantuan(penerima.id, penerima.status, penerima.tglpengajuan, penerima.bantuan, penerima.keluarga)
            .enqueue(object : Callback<ResultSimple>{
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        addPenerimaBantuanView.onSuccessAddPenerimaBantuan(body.message)
                    }else{
                        addPenerimaBantuanView.onErrorAddPenerimaBantuan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable) {
                    addPenerimaBantuanView.onErrorAddPenerimaBantuan(t.localizedMessage)
                }

            })
    }

    fun updatePenerimaBantuan(penerima: ModelPenerimaBantuan){
        SambangUtils.getservice()
            .updatePenerimaBantuan(penerima.id, penerima.status, penerima.tglpengajuan, penerima.bantuan, penerima.keluarga)
            .enqueue(object : Callback<ResultSimple>{
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        addPenerimaBantuanView.onSuccessAddPenerimaBantuan(body.message)
                    }else{
                        addPenerimaBantuanView.onErrorAddPenerimaBantuan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}