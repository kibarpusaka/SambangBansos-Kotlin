package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelPenerimaBantuan(
    @SerializedName("id")
    @Expose
    var id : Int? = null,

    @SerializedName("Status")
    @Expose
    var status : String? = null,

    @SerializedName("TglPengajuan")
    @Expose
    var tglpengajuan : String? = null,

    @SerializedName("Bantuan")
    @Expose
    var bantuan : Int? = null,

    @SerializedName("Keluarga")
    @Expose
    var keluarga : Int? = null,

    var namaBantuan : String = ""

) : Serializable


