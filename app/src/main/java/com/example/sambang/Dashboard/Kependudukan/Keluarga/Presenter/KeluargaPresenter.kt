package com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.SharedPref.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeluargaPresenter( val dataKeluargaView: DataKeluargaView)
{
    private val keluarga = mutableListOf<ModelKeluarga>()
    private val villages = mutableListOf<ModelDesaMaster>()


    private fun getNamaDesaOnly(desaId : String?) : String{
        if(desaId == null){
            return ""
        }
        val desa = villages.find { v -> v.id!! == desaId } ?: return ""
        return desa.nama ?: ""
    }

    fun showDataKeluarga(){
        dataKeluargaView.onSuccessDataKeluarga(keluarga)
    }

    fun filterData(q : String) {
        val filterd = keluarga.filter { v -> v.nomerkk!!.toLowerCase().contains(q.toLowerCase())}
        dataKeluargaView.onSuccessDataKeluarga(filterd)
    }
   private fun getDataKeluarga(token : String) {
        SambangUtils.getservice()
            .getKeluarga(token = "Token ${token}")
            .enqueue(object : Callback<ResponKeluarga> {
                override fun onResponse(
                    call: Call<ResponKeluarga>,
                    response: Response<ResponKeluarga>){
                    val body = response.body()
                    if (body?.status == true) {
                        keluarga.clear()
                        keluarga.addAll(keluarga)
                        dataKeluargaView.onSuccessDataKeluarga(body.data_keluarga)

                        val wargas = body.data_keluarga ?: mutableListOf()
                        wargas.forEach { w ->
                            w.namaDesa = getNamaDesaOnly(w.desa)
                        }
                        dataKeluargaView.onSuccessDataKeluarga(wargas)
                    } else
                    {
                        dataKeluargaView.onErrorDataKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable) {
                    dataKeluargaView.onErrorDataKeluarga(t.localizedMessage)
                }
            })
    }

    fun getDataDesa(token: String){
        println(token)
        SambangUtils.getservice()
            .getDesa(token = "Token ${token}")
            .enqueue(object : Callback<ResponDesa>{
                override fun onResponse(call: Call<ResponDesa>, response: Response<ResponDesa>) {
                    val body = response.body()
                    if (body?.status == true) {
                        villages.clear()
                        villages.addAll(body.data_desa)
                        getDataKeluarga(token)
                    }else{
                        dataKeluargaView.onErrorDataKeluarga("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    dataKeluargaView.onErrorDataKeluarga(t.localizedMessage)
                }

            })

    }

    fun deleteKeluarga(token: String, keluarga: ModelKeluarga)
    {
        SambangUtils.getservice()
            .deleteKeluarga(token = "Token ${token}", id = keluarga.id)
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
                        dataKeluargaView.onSuccessDeleteKeluarga(body.message)
                    } else
                    {
                        dataKeluargaView.onErrorDeleteKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    dataKeluargaView.onErrorDeleteKeluarga(t.localizedMessage)
                }
            })
    }
}