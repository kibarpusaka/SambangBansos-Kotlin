package com.example.sambang.Api

import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ResponPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Dashboard.Master.Bantuan.Data.ResponBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ResponDesa
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ResponKecamatan
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ResponDaftarUsulan
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
    @GET("/api/desa/")
    fun getDesa(@Header("Authorization") token: String?) : Call<ResponDesa>

    /***
     *
     * KECAMATAN
     *
     */
    @GET("/api/kecamatan/")
    fun getKecamatan(@Header("Authorization") token: String?) : Call<ResponKecamatan>

    /***
     *
     * DAFTAR USULAN
     *
     */
    @GET("/api/warga/")
    fun getDaftarUsulan(@Header("Authorization") token: String?) : Call<ResponDaftarUsulan>

    /***
     *
     * BANTUAN
     *
     */
    @GET("/api/bantuan/")
    fun getBantuan(@Header("Authorization") token: String?) : Call<ResponBantuanMaster>

    /***
     *
     * NIK NON AKTIF
     *
     */
    //NIKAKTIF
    @GET("/api/warga/")
    fun getNikNonAktif(@Header("Authorization") token: String?) : Call<ResponNikAktif>

    /***
     *
     * CHECK NIK
     *
     */
    //NIKAKTIF
    @GET("/api/warga/")
    fun getCheckNik(@Header("Authorization") token: String?) : Call<ResponNikAktif>


    /***
     *
     * PENERIMA BANTUAN
     *
     */
    @GET("/api/penerimabantuan/")
    fun getPenerimaBantuan(@Header("Authorization") token: String?) : Call<ResponPenerimaBantuan>

    @FormUrlEncoded
    @POST("/api/penerimabantuan/")
    fun addPenerimaBantuan(
        @Field("id") id: Int?,
        @Field("Status") Status: Int?,
        @Field("TglPengajuan") TglPengajuan: String?,
        @Field("Bantuan") Bantuan: Int?,
        @Field("Keluarga") Keluarga: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @PUT("/api/penerimabantuan/{id}/")
    fun updatePenerimaBantuan(
        @Path("id") id: Int?,
        @Field("Status") Status: Int?,
        @Field("TglPengajuan") TglPengajuan: String?,
        @Field("Bantuan") Bantuan: Int?,
        @Field("Keluarga") Keluarga: Int?
    ) : Call<ResultSimple>

    @DELETE("/api/penerimabantuan/{id}/")
    fun deletePenerimaBantuan(
        @Path("id") id: Int?,
    ) : Call<ResultSimple>



    /***
     *
     * KELUARGA
     *
     */
    //Keluarga
    @GET("/api/keluarga/")
    fun getKeluarga(@Header("Authorization") token: String?
    ) : Call<ResponKeluarga>

    @FormUrlEncoded
    @POST("/api/keluarga/")
    fun addKeluarga(
        @Field("id") id: Int?,
        @Field("NomerKK") NomerKK: String?,
        @Field("Alamat") Alamat: String?,
        @Field("Rt") Rt: Int?,
        @Field("Rw") Rw: Int?,
        @Field("Desa") Desa: Int?,
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @PUT("/api/keluarga/{id}/")
    fun updateKeluarga(
        @Path("id") id: Int?,
        @Field("NomerKK") NomerKK: String?,
        @Field("Alamat") Alamat: String?,
        @Field("Rt") Rt: Int?,
        @Field("Rw") Rw: Int?,
        @Field("Desa") Desa: Int?,
        ) : Call<ResultSimple>


    @DELETE("/api/keluarga/{id}/")
    fun deleteKeluarga(
        @Path("id") id: Int?,
    ) : Call<ResultSimple>


    /***
     *
     * NIK AKTIF
     *
     */
    @GET("/api/warga/")
    fun getNikAktif(@Header("Authorization") token: String?) : Call<ResponNikAktif>
    @FormUrlEncoded
    @POST("/api/warga/")
    fun addNikAktif(
        @Field("id") id: Int?,
        @Field("Nik") nik: String?,
        @Field("Nama") nama: String?,
        @Field("TmpLahir") tempatlahir: String?,
        @Field("TglLahir") tanggallahir: String?,
        @Field("Alamat") alamat: String?,
        @Field("Rt") rt: Int?,
        @Field("Rw") rw: Int?,
        @Field("NikValid") nikvalid: Boolean?,
        @Field("Desa") desa: Int?,
        @Field("Keluarga") keluarga: Int?
    ) : Call<ResultSimple>

    @FormUrlEncoded
    @PUT("/api/warga/{id}/")
    fun updateNikAktif(
        @Path("id") id: Int?,
        @Field("Nik") nik: String?,
        @Field("Nama") nama: String?,
        @Field("TmpLahir") tempatlahir: String?,
        @Field("TglLahir") tanggallahir: String?,
        @Field("Alamat") alamat: String?,
        @Field("Rt") rt: Int?,
        @Field("Rw") rw: Int?,
        @Field("NikValid") nikvalid: Boolean?,
        @Field("Desa") desa: Int?,
        @Field("Keluarga") keluarga: Int?
    ) : Call<ResultSimple>

    @DELETE("/api/warga/{id}/")
    fun deleteNikAktif(
        @Path("id") id: Int?
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