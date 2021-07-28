package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ModelDaftarUsulan
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ResponDaftarUsulan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarUsulanPresenter(val dataDaftarUsulanView: DaftarUsulanView) {

    private val usulan = mutableListOf<ModelNikAktif>()
    private var family = mutableListOf<ModelKeluarga>()
    private var villages = mutableListOf<ModelDesaMaster>()

    private fun getNoKK(keluargaId: String?) : String{
        if (keluargaId == null){
            return ""
        }
        val keluarga = family.find { k -> k.id == keluargaId } ?: return ""
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
        dataDaftarUsulanView.onSuccessDataDaftarUsulan(usulan)
    }

    fun filterData(q: String){
        val filtered = usulan.filter { v -> v.nama!!.toLowerCase().contains(q.toLowerCase()) }
        dataDaftarUsulanView.onSuccessDataDaftarUsulan(filtered)
    }

    private fun getDataDaftarUsulan(token : String){
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
                        keluargas.forEach { s ->
                            s.noKK = getNoKK(s.keluarga)
                        }

                        val wargas = body.data_warga ?: mutableListOf()
                        wargas.forEach { w ->
                            w.namaDesa = getNamaDesaOnly(w.desa.toString())
                        }

                        usulan.clear()
                        usulan.addAll(body.data_warga)
                        dataDaftarUsulanView.onSuccessDataDaftarUsulan(body.data_warga)

                        val filter = body.data_warga?.filter { f -> f.status == "usulan"}
                        dataDaftarUsulanView.onSuccessDataDaftarUsulan(filter)

                    }else{
                        dataDaftarUsulanView.onErrorDataDaftarUsulan(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponNikAktif>, t: Throwable) {
                    dataDaftarUsulanView.onErrorDataDaftarUsulan(t.localizedMessage)
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
                        getDataDaftarUsulan(token)
                    } else
                    {
                        dataDaftarUsulanView.onErrorDataDaftarUsulan("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable) {
                    dataDaftarUsulanView.onErrorDataDaftarUsulan(t.localizedMessage)
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
                        getDataDaftarUsulan(token)
                    }else{
                        dataDaftarUsulanView.onErrorDataDaftarUsulan("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    dataDaftarUsulanView.onErrorDataDaftarUsulan(t.localizedMessage)
                }

            })

    }
}