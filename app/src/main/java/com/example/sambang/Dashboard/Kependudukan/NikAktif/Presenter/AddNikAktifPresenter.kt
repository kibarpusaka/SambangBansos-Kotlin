package com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter

import com.example.sambang.Dashboard.Kependudukan.NikAktif.AddNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
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

    fun getDataDesa(token : String){
        SambangUtils.getservice()
            .getDesa(token = "Token ${token}")
            .enqueue(object : Callback<ResponDesa>{
                override fun onResponse(call: Call<ResponDesa>, response: Response<ResponDesa>) {
                    val body = response.body()
                    if (body?.status == true) {
                        addNikAktifView.attachSpiner(body.data_desa)
                    }else {
                        addNikAktifView.onErrorAddNikAktif(body?.message)
                    }
                }
                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    addNikAktifView.onErrorAddNikAktif(t.localizedMessage)
                }
            })
    }

    fun getDataKeluarga(token : String) {
        SambangUtils.getservice()
            .getKeluarga(token = "Token ${token}")
            .enqueue(object : Callback<ResponKeluarga> {
                override fun onResponse(
                    call: Call<ResponKeluarga>,
                    response: Response<ResponKeluarga>){
                    val body = response.body()
                    if (body?.status == true) {
                        addNikAktifView.attachSpinerKeluarga(body.data_keluarga)
                    } else
                    {
                        addNikAktifView.onErrorAddNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable) {
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