package com.example.sambang.Dashboard.Kependudukan.NikAktif.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelNikAktif(

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("Nik")
    @Expose
    var nik: String? = null,

    @SerializedName("Nama")
    @Expose
    var nama: String? = null,

    @SerializedName("TmpLahir")
    @Expose
    var tempatlahir: String? = null,

    @SerializedName("TglLahir")
    @Expose
    var tanggallahir: String? = null,

    @SerializedName("Alamat")
    @Expose
    var alamat: String? = null,

    @SerializedName("Rt")
    @Expose
    var rt: String? = null,

    @SerializedName("Rw")
    @Expose
    var rw: String? = null,

    @SerializedName("NikValid")
    @Expose
    var nikvalid: Boolean? = null,

    @SerializedName("Status")
    @Expose
    var status: String? = null,

    @SerializedName("Desa")
    @Expose
    var desa: String? = null,

    @SerializedName("Keluarga")
    @Expose
    var keluarga: String? = null,

    var namaDesa: String = "",

    var noKK : String = ""

) : Serializable

