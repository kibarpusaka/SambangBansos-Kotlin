package com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter

import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NikAktifPresenter(val dataNikAktifView: DataNikAktifView)
{
    private val nikAktif = mutableListOf<ModelNikAktif>()
    private var family = mutableListOf<ModelKeluarga>()
    private var villages = mutableListOf<ModelDesaMaster>()

    private fun getNoKK(keluargaId: String) : String {
        if (keluargaId == null){
            return ""
        }
        val keluarga = family.find { v -> v.id == keluargaId } ?: return ""
        return keluarga.nomerkk ?: ""
    }

    private fun getNamaDesaOnly(desaId : String?) : String{
        if(desaId == null){
            return ""
        }
        val desa = villages.find { v -> v.id!! == desaId } ?: return ""
        return desa.nama ?: ""
    }

    fun showDataNikAktif(){
        dataNikAktifView.onSuccessDataNikAktif(nikAktif)
    }

    fun filterData(q : String){
        val filter = nikAktif.filter { v -> v.nama!!.toLowerCase().contains(q.toLowerCase()) }
        dataNikAktifView.onSuccessDataNikAktif(filter)
    }
    fun getNikAktif(token : String)
    {
        SambangUtils.getservice()
            .getWarga(token = "Token ${token}")
            .enqueue(object : Callback<ResponNikAktif>
            {
                override fun onResponse(
                    call: Call<ResponNikAktif>,
                    response: Response<ResponNikAktif>
                ) {

                    val body = response.body()
                    if (body?.status == true)
                    {
                        val keluargas = body.data_warga ?: mutableListOf()
                        keluargas.forEach { w ->
                            w.noKK = getNoKK(w.keluarga.toString())
                        }

                        val wargas = body.data_warga ?: mutableListOf()
                        wargas.forEach { w ->
                            w.namaDesa = getNamaDesaOnly(w.desa.toString())
                        }
                        nikAktif.clear()
                        nikAktif.addAll(body.data_warga)
                        val filtered = body.data_warga?.filter { w -> w.nikvalid == true }
                        dataNikAktifView.onSuccessDataNikAktif(filtered)

                    } else
                    {
                        dataNikAktifView.onErrorDataNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponNikAktif>, t: Throwable)
                {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
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
                        family.clear()
                        family.addAll(body.data_keluarga)
                        getNikAktif(token)
                    } else
                    {
                        dataNikAktifView.onErrorDataNikAktif("Cannot fetch data of no kk")
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable) {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
                }
            })
    }

    fun getDataDesa(token: String){
        SambangUtils.getservice()
            .getDesa(token = "Token ${token}")
            .enqueue(object : Callback<ResponDesa>{
                override fun onResponse(call: Call<ResponDesa>, response: Response<ResponDesa>) {
                    val body = response.body()
                    if (body?.status == true) {
                        villages.clear()
                        villages.addAll(body.data_desa)
                        getNikAktif(token)
                    }else{
                        dataNikAktifView.onErrorDataNikAktif("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
                }

            })

    }

    fun deleteNikAktif(token: String, nikAktif: ModelNikAktif?)
    {
        SambangUtils.getservice()
            .deleteWarga(token = "Token ${token}",nikAktif?.id)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataNikAktifView.onSuccessDeleteNikAktif(body.message)
                    } else {
                        dataNikAktifView.onErrorDeleteNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    dataNikAktifView.onErrorDeleteNikAktif(t.localizedMessage)
                }
            })
    }
}