package com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter

import com.example.sambang.Dashboard.Kependudukan.Keluarga.AddKeluargaView
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddKeluargaPresenter(val addKeluargaView: AddKeluargaView)
{

    fun addKeluarga(token : String,keluarga: ModelKeluarga)
    {
        SambangUtils.getservice()
            .addKeluarga(token = "Token ${token}",keluarga.id, keluarga.nomerkk, keluarga.alamat, keluarga.rt, keluarga.rw, keluarga.desa)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true)
                    {
                        addKeluargaView.onSuccessAddKeluarga(body.message)
                    } else
                    {
                        addKeluargaView.onErrorAddKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    addKeluargaView.onErrorAddKeluarga(t.localizedMessage)
                }
            })
    }

    fun getDataDesa(token : String){
        SambangUtils.getservice()
            .getDesa(token = "Token ${token}")
            .enqueue(object : Callback<ResponDesa>{
                override fun onResponse(call: Call<ResponDesa>, response: Response<ResponDesa>) {
                    val body = response.body()
                    if (body?.status == true) {
                        addKeluargaView.attachSpiner(body.data_desa)
                    }else {
                        addKeluargaView.onErrorAddKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    addKeluargaView.onErrorAddKeluarga(t.localizedMessage)
                }

            })

    }

    fun updateKeluarga(token: String,keluarga: ModelKeluarga)
    {
        SambangUtils.getservice()
            .updateKeluarga(token = "Token ${token}",keluarga.id, keluarga.nomerkk, keluarga.alamat, keluarga.rt, keluarga.rw, keluarga.desa)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true)
                    {
                        addKeluargaView.onSuccessAddKeluarga(body.message)
                    } else
                    {
                        addKeluargaView.onErrorAddKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    addKeluargaView.onErrorAddKeluarga(t.localizedMessage)
                }

            })
    }

}