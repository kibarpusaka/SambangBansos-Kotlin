package com.example.sambang.Api

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster
import com.example.sambang.Login.Data.ResponLogin
import com.example.sambang.Utils.ResultSimple
import retrofit2.Call
import retrofit2.http.*

interface ApiSambang {
    @GET("/api/desa/")
    fun getDesa() : Call<List<ModelDesaMaster>>

    @GET("/api/kecamatan/")
    fun getKecamatan() : Call<List<ModelKecamatanMaster>>


    /***
     *
     * KELUARGA
     *
     */
    //Keluarga
    @FormUrlEncoded
    @POST("/api/keluarga/")
    fun getKeluarga(
        @Field("id")id: Int?
    ) : Call<ResponKeluarga>

    @FormUrlEncoded
    @POST("")
    fun addKeluarga(
        @Field("id") id: Int?,
        @Field("NomerKK") NomerKK: String?,
        @Field("Alamat") Alamat: String?,
        @Field("Rt") Rt: Int?,
        @Field("Rw") Rw: Int?,
        @Field("Desa") Desa: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @POST("")
    fun updateKeluarga(
        @Field("id") id: Int?,
        @Field("NomerKK") NomerKK: String?,
        @Field("Alamat") Alamat: String?,
        @Field("Rt") Rt: Int?,
        @Field("Rw") Rw: Int?,
        @Field("Desa") Desa: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @POST("")
    fun deleteKeluarga(
        @Field("id") id: Int?,
        parseInt: Int,
        nomerkk: String?
    ) : Call<ResultSimple>


    /***
     *
     * NIK AKTIF
     *
     */
    //NIKAKTIF
    @GET("")
    fun getNikAktif(
        @Field("id")id: Int?
    ) : Call<ResponNikAktif>

    @FormUrlEncoded
    @POST("")
    fun addNikAktif(
        @Field("id") id: String?,
        @Field("Nik") Nik: String?,
        @Field("Nama") Nama: String?,
        @Field("TmpLahir") TmpLahir: String?,
        @Field("TglLahir") TglLahir: String?,
        @Field("Alamat") Alamat: Int?,
        @Field("Rt") Rt: Int?,
        @Field("Rw") Rw: Boolean?,
        @Field("NikValid") NikValid: Int?,
        @Field("Desa") Desa: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @POST("")
    fun updateNikAktif(
        @Field("id") id: String?,
        @Field("Nik") nik: String?,
        @Field("Nama") nama: String?,
        @Field("TmpLahir") tempatlahir: String?,
        @Field("TglLahir") tanggallahir: String?,
        @Field("Alamat") alamat: Int?,
        @Field("Rt") rt: Int?,
        @Field("Rw") rw: Boolean?,
        @Field("NikValid") nikvalid: Int?,
        @Field("Desa") Desa: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @POST("")
    fun deleteNikAktif(
        @Field("id") id: Int?,
        parseInt: Int,
        nik: String?
    ) : Call<ResultSimple>


    /***
     *
     * LOGIN
     *
     */
    //LOGIN
    @FormUrlEncoded
    @POST("/api/api-token-auth/")
    fun loginUser (
        @Field("username") username: String?,
        @Field("password") password: String?
    ) : Call<ResponLogin>
}