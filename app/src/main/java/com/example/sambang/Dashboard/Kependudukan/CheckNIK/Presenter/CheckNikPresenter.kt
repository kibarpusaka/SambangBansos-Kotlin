package com.example.sambang.Dashboard.Kependudukan.CheckNIK.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckNikPresenter(val dataNikAktifView: DataNikAktifView) {

    private val checkNik = mutableListOf<ModelNikAktif>()
    private var family = mutableListOf<ModelKeluarga>()
    private var villages = mutableListOf<ModelDesaMaster>()


    private fun getNoKK(keluargaId: String?) : String {
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

    fun showAllData(){
        dataNikAktifView.onSuccessDataNikAktif(checkNik)
    }

    fun filterData(q: String){
        val filter = checkNik.filter { v -> v.nama!!.toLowerCase().contains(q.toLowerCase()) }
        dataNikAktifView.onSuccessDataNikAktif(filter)
    }

    fun getCheckNik(token : String){
        SambangUtils.getservice()
            .getWarga(token = "Token ${token}")
            .enqueue(object : Callback<ResponNikAktif>{
                override fun onResponse(
                    call: Call<ResponNikAktif>,
                    response: Response<ResponNikAktif>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        val keluargas = body.data_warga ?: mutableListOf()
                        keluargas.forEach { w ->
                            w.noKK = getNoKK(w.keluarga)
                        }

                        val wargas = body.data_warga ?: mutableListOf()
                        wargas.forEach { w ->
                            w.namaDesa = getNamaDesaOnly(w.desa.toString())
                        }

                        checkNik.clear()
                        checkNik.addAll(body.data_warga)
                        dataNikAktifView.onSuccessDataNikAktif(body.data_warga)
                    }else{
                        dataNikAktifView.onErrorDataNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponNikAktif>, t: Throwable) {
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
                        getCheckNik(token)
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
                        getCheckNik(token)
                    }else{
                        dataNikAktifView.onErrorDataNikAktif("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
                }

            })
    }
}