package com.example.sambang.Api

import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ResponPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Dashboard.Master.Bantuan.Data.ResponBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ResponKecamatan
import com.example.sambang.Login.Data.ResponLogin
import com.example.sambang.Utils.ResultSimple
import retrofit2.Call
import retrofit2.http.*

interface ApiSambang {
    /***
     *
     * DESA
     *
     */
    @GET("/api/v1/desa/")
    fun getDesa(@Header("Authorization") token: String?) : Call<ResponDesa>

    /***
     *
     * KECAMATAN
     *
     */
    @GET("/api/v1/kecamatan/")
    fun getKecamatan(@Header("Authorization") token: String?) : Call<ResponKecamatan>

    /***
     *
     * BANTUAN
     *
     */
    @GET("/api/v1/bantuan/")
    fun getBantuan(@Header("Authorization") token: String?) : Call<ResponBantuanMaster>

    /***
     *
     * PENERIMA BANTUAN
     *
     */
    @GET("/api/v1/penerimabantuan/")
    fun getPenerimaBantuan(@Header("Authorization") token: String?) : Call<ResponPenerimaBantuan>

    @FormUrlEncoded
    @POST("/api/v1/penerimabantuan/")
    fun addPenerimaBantuan(
        @Header("Authorization") token: String?,
        @Field("id") id: Int?,
        @Field("Status") Status: String?,
        @Field("TglPengajuan") TglPengajuan: String?,
        @Field("Bantuan") Bantuan: Int?,
        @Field("Keluarga") Keluarga: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @PUT("/api/v1/penerimabantuan/{id}/")
    fun updatePenerimaBantuan(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?,
        @Field("Status") Status: String?,
        @Field("TglPengajuan") TglPengajuan: String?,
        @Field("Bantuan") Bantuan: Int?,
        @Field("Keluarga") Keluarga: Int?
    ) : Call<ResultSimple>

    @DELETE("/api/v1/penerimabantuan/{id}/")
    fun deletePenerimaBantuan(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?,
    ) : Call<ResultSimple>



    /***
     *
     * KELUARGA
     *
     */
    //Keluarga
    @GET("/api/v1/keluarga/")
    fun getKeluarga(@Header("Authorization") token: String?) : Call<ResponKeluarga>

    @FormUrlEncoded
    @POST("/api/v1/keluarga/")
    fun addKeluarga(
        @Header("Authorization") token: String?,
        @Field("id") id: String?,
        @Field("NomerKK") NomerKK: String?,
        @Field("Alamat") Alamat: String?,
        @Field("Rt") Rt: String?,
        @Field("Rw") Rw: String?,
        @Field("Desa") Desa: String?,
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @PUT("/api/v1/keluarga/{id}/")
    fun updateKeluarga(
        @Header("Authorization") token: String?,
        @Path("id") id: String?,
        @Field("NomerKK") NomerKK: String?,
        @Field("Alamat") Alamat: String?,
        @Field("Rt") Rt: String?,
        @Field("Rw") Rw: String?,
        @Field("Desa") Desa: String?,
        ) : Call<ResultSimple>


    @DELETE("/api/v1/keluarga/{id}/")
    fun deleteKeluarga(
        @Header("Authorization") token: String?,
        @Path("id") id: String?,
    ) : Call<ResultSimple>


    /***
     *
     * WARGA
     *
     */
    @GET("/api/v1/warga/")
    fun getWarga(@Header("Authorization") token: String?) : Call<ResponNikAktif>

    @FormUrlEncoded
    @POST("/api/v1/warga/")
    fun addWarga(
        @Header("Authorization") token: String?,
        @Field("id") id: Int?,
        @Field("Nik") nik: String?,
        @Field("Nama") nama: String?,
        @Field("TmpLahir") tempatlahir: String?,
        @Field("TglLahir") tanggallahir: String?,
        @Field("Alamat") alamat: String?,
        @Field("Rt") rt: String?,
        @Field("Rw") rw: String?,
        @Field("NikValid") nikvalid: Boolean?,
        @Field("Desa") desa: String?,
        @Field("Keluarga") keluarga: String?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @PUT("/api/v1/warga/{id}/")
    fun updateWarga(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?,
        @Field("Nik") nik: String?,
        @Field("Nama") nama: String?,
        @Field("TmpLahir") tempatlahir: String?,
        @Field("TglLahir") tanggallahir: String?,
        @Field("Alamat") alamat: String?,
        @Field("Rt") rt: String?,
        @Field("Rw") rw: String?,
        @Field("NikValid") nikvalid: Boolean?,
        @Field("Desa") desa: String?,
        @Field("Keluarga") keluarga: String?
    ) : Call<ResultSimple>

    @DELETE("/api/v1/warga/{id}/")
    fun deleteWarga(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ) : Call<ResultSimple>

    /***
     *
     * LOGIN
     *
     */
    //LOGIN
    @FormUrlEncoded
    @POST("/api/v1/api-token-auth/")
    fun loginUser (
        @Field("username") username: String?,
        @Field("password") password: String?
    ) : Call<ResponLogin>
}