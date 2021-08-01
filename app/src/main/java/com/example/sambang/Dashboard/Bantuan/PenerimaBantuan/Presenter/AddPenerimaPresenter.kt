package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.AddPenerimaView
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Master.Bantuan.Data.ResponBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.Utils.ResultSimple
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPenerimaPresenter(val addPenerimaView: AddPenerimaView) {

    fun addPenerima(token: String,dataNikAktif: ModelNikAktif)
    {
        SambangUtils.getservice()
            .addPenerima(token = "Token ${token}",dataNikAktif.id, dataNikAktif.nik, dataNikAktif.nama, dataNikAktif.tempatlahir, dataNikAktif.tanggallahir, dataNikAktif.alamat, dataNikAktif.rt, dataNikAktif.rw, dataNikAktif.nikvalid, dataNikAktif.desa, dataNikAktif.keluarga, dataNikAktif.bantuan)
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
                        addPenerimaView.onSuccessAddPenerima(body.message)
                    } else
                    {
                        addPenerimaView.onErrorAddPenerima(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    addPenerimaView.onErrorAddPenerima(t.localizedMessage)
                }
            })
    }

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
                        addPenerimaView.attachSpinerBantua(body.data)
                    }else{
                        addPenerimaView.onErrorAddPenerima(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponBantuanMaster>, t: Throwable) {
                    addPenerimaView.onErrorAddPenerima(t.localizedMessage)
                }

            })
    }

    fun getDataDesa(token : String){
        SambangUtils.getservice()
            .getDesa(token = "Token ${token}")
            .enqueue(object : Callback<ResponDesa> {
                override fun onResponse(call: Call<ResponDesa>, response: Response<ResponDesa>) {
                    val body = response.body()
                    if (body?.status == true) {
                        addPenerimaView.attachSpiner(body.data_desa)
                    }else {
                        addPenerimaView.onErrorAddPenerima(body?.message)
                    }
                }
                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    addPenerimaView.onErrorAddPenerima(t.localizedMessage)
                }
            })
    }

    fun getDataKeluarga(token : String) {
        SambangUtils.getservice()
            .getKeluarga(token = "Token ${token}")
            .enqueue(object : Callback<ResponKeluarga> {
                override fun onResponse(
                    call: Call<ResponKeluarga>,
                    response: Response<ResponKeluarga>
                ){
                    val body = response.body()
                    if (body?.status == true) {
                        addPenerimaView.attachSpinerKeluarga(body.data_keluarga)
                    } else
                    {
                        addPenerimaView.onErrorAddPenerima(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable) {
                    addPenerimaView.onErrorAddPenerima(t.localizedMessage)
                }
            })
    }

    fun updateNikAktif(token : String,dataNikAktif: ModelNikAktif)
    {
        SambangUtils.getservice()
            .updatePenerima(token = "Token ${token}",dataNikAktif.id, dataNikAktif.nik, dataNikAktif.nama, dataNikAktif.tempatlahir, dataNikAktif.tanggallahir, dataNikAktif.alamat, dataNikAktif.rt, dataNikAktif.rw, dataNikAktif.nikvalid, dataNikAktif.desa, dataNikAktif.keluarga, dataNikAktif.bantuan)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body =  response.body()
                    if (body?.status == true)
                    {
                        addPenerimaView.onSuccessAddPenerima(body.message)
                    } else
                    {
                        addPenerimaView.onErrorAddPenerima(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable) {
                    addPenerimaView.onErrorAddPenerima(t.localizedMessage)
                }
            })
    }
}