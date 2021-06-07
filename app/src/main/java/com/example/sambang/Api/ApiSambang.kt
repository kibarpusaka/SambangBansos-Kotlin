package com.example.sambang.Api

import com.example.sambang.Dashboard.Kependudukan.Keluarga.ModelKeluarga
import com.example.sambang.Dashboard.Master.Desa.Model.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Kecamatan.ModelKecamatanMaster
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiSambang {
    @GET("desas")
    fun getDesa() : Call<List<ModelDesaMaster>>

    @POST("keluargaks")
    fun createDesa(@Body req: ModelKeluarga) : Call<ModelKeluarga>
    abstract fun createDesa(): Call<ModelKeluarga >

    @GET("kecamatans")
    fun getKecamatan() : Call<List<ModelKecamatanMaster>>

    @GET("keluargaks")
    fun getKeluarga() : Call<List<ModelKeluarga>>
}