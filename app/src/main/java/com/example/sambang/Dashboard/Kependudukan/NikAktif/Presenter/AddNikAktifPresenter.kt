package com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter

import com.example.sambang.Dashboard.Kependudukan.NikAktif.AddNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNikAktifPresenter(val addNikAktifView: AddNikAktifView)
{
    fun addNikAktif(token: String,dataNikAktif: ModelNikAktif )
    {
        SambangUtils.getservice()
            .addWarga(token = "Token ${token}",dataNikAktif.id, dataNikAktif.nik, dataNikAktif.nama, dataNikAktif.tempatlahir, dataNikAktif.tanggallahir, dataNikAktif.alamat, dataNikAktif.rt, dataNikAktif.rw, dataNikAktif.nikvalid, dataNikAktif.desa, dataNikAktif.keluarga)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                )
                {
                    val body = response.body()
                    if (body?.status == true)
                    {
                        addNikAktifView.onSuccessAddNikAktif(body.message)
                    } else
                    {
                        addNikAktifView.onErrorAddNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    addNikAktifView.onErrorAddNikAktif(t.localizedMessage)
                }
            })
    }

    fun updateNikAktif(token : String,dataNikAktif: ModelNikAktif)
    {
        SambangUtils.getservice()
            .updateWarga(token = "Token ${token}",dataNikAktif.id, dataNikAktif.nik, dataNikAktif.nama, dataNikAktif.tempatlahir, dataNikAktif.tanggallahir, dataNikAktif.alamat, dataNikAktif.rt, dataNikAktif.rw, dataNikAktif.nikvalid, dataNikAktif.desa, dataNikAktif.keluarga)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body =  response.body()
                    if (body?.status == true)
                    {
                        addNikAktifView.onSuccessAddNikAktif(body.message)
                    } else
                    {
                        addNikAktifView.onErrorAddNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable) {
                    addNikAktifView.onErrorAddNikAktif(t.localizedMessage)
                }
            })
    }
}