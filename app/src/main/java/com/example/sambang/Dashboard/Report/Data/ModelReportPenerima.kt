package com.example.sambang.Dashboard.Report.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelReportPenerima(
    @SerializedName("id")
    @Expose
    var id : Int? = null,

    @SerializedName("Status")
    @Expose
    var status : Int? = null,

    @SerializedName("TglPengajuan")
    @Expose
    var tglpengajuan : String? = null,

    @SerializedName("Bantuan")
    @Expose
    var bantuan : Int? = null,

    @SerializedName("Keluarga")
    @Expose
    var keluarga : Int? = null
) : Serializable
