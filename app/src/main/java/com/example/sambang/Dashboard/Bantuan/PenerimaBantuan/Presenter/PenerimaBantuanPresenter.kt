package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter

import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ResponPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Master.Bantuan.Data.ModelBantuanMaster
import com.example.sambang.Dashboard.Master.Bantuan.Data.ResponBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.ResultSimple
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PenerimaBantuanPresenter(val dataPenerimaBantuanView: DataPenerimaBantuanView) {
    private val penerima = mutableListOf<ModelNikAktif>()
    private var family = mutableListOf<ModelKeluarga>()
    private var villages = mutableListOf<ModelDesaMaster>()
    private var bantuan = mutableListOf<ModelBantuanMaster>()

    private fun getNamaBantuan(bantuanId : String) : String{
        if (bantuanId == null){
            return ""
        }
        val bantuan = bantuan.find { b -> b.id == bantuanId} ?: return ""
        return bantuan.nama ?: ""
    }

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
        dataPenerimaBantuanView.onSuccessDataPenerimaBantuan(penerima)
    }

    fun filterData(q: String){
        val filtered = penerima.filter { v -> v.nama!!.toLowerCase().contains(q.toLowerCase()) }
        dataPenerimaBantuanView.onSuccessDataPenerimaBantuan(filtered)
    }
    fun getDataPenerimaBantuan(token : String){
        SambangUtils.getservice()
            .getPenerimaBantuan(token = "Token ${token}")
            .enqueue(object : Callback<ResponPenerimaBantuan>{
                override fun onResponse(
                    call: Call<ResponPenerimaBantuan>,
                    response: Response<ResponPenerimaBantuan>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        val keluargas = body.data_penerima_bantuan ?: mutableListOf()
                        keluargas.forEach { s ->
                            s.noKK = getNoKK(s.keluarga)
                        }

                        val wargas = body.data_penerima_bantuan ?: mutableListOf()
                        wargas.forEach { w ->
                            w.namaDesa = getNamaDesaOnly(w.desa.toString())
                        }

                        val bantuans = body.data_penerima_bantuan ?: mutableListOf()
                        bantuans.forEach { b ->
                            b.namaBantuan = getNamaBantuan(b.bantuan.toString())
                        }

                        penerima.clear()
                        penerima.addAll(body.data_penerima_bantuan)
                        dataPenerimaBantuanView.onSuccessDataPenerimaBantuan(body.data_penerima_bantuan)
                    }else{
                        dataPenerimaBantuanView.onErrorDataPenerimaBantuan(body?.message)
                    }
                }
                override fun onFailure(call: Call<ResponPenerimaBantuan>, t: Throwable) {
                    dataPenerimaBantuanView.onErrorDataPenerimaBantuan(t.localizedMessage)
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
                        bantuan.clear()
                        bantuan.addAll(body.data)
                        getDataPenerimaBantuan(token)
                    }else{
                        dataPenerimaBantuanView.onErrorDataPenerimaBantuan("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponBantuanMaster>, t: Throwable) {
                    dataPenerimaBantuanView.onErrorDataPenerimaBantuan(t.localizedMessage)
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
                        getDataPenerimaBantuan(token)
                    } else
                    {
                        dataPenerimaBantuanView.onErrorDataPenerimaBantuan("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable) {
                    dataPenerimaBantuanView.onErrorDataPenerimaBantuan(t.localizedMessage)
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
                        getDataPenerimaBantuan(token)
                    }else{
                        dataPenerimaBantuanView.onErrorDataPenerimaBantuan("Cannot fetch data of desa")
                    }
                }

                override fun onFailure(call: Call<ResponDesa>, t: Throwable) {
                    dataPenerimaBantuanView.onErrorDataPenerimaBantuan(t.localizedMessage)
                }

            })

    }

}