package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelDaftarUsulan(

    @SerializedName("id")
    @Expose
    var id : Int? = null,

    @SerializedName("Nik")
    @Expose
    var nik : String? = null,

    @SerializedName("Nama")
    @Expose
    var nama : String? = null,

    @SerializedName("TmpLahir")
    @Expose
    var tempatlahir : String? = null,

    @SerializedName("TglLahir")
    @Expose
    var tanggallahir : String? = null,

    @SerializedName("Alamat")
    @Expose
    var alamat : String? = null,

    @SerializedName("Rt")
    @Expose
    var rt : Int? = null,

    @SerializedName("Rw")
    @Expose
    var rw : Int? = null,

    @SerializedName("NikValid")
    @Expose
    var nikvalid : Boolean? = null,

    @SerializedName("Desa")
    @Expose
    var desa : Int? = null,

    @SerializedName("Keluarga")
    @Expose
    var keluarga : Int? = null
) : Serializable
