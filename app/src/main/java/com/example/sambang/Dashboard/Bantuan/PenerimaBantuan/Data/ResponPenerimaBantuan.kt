package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.google.gson.annotations.SerializedName

data class ResponPenerimaBantuan(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data_penerima_bantuan")
    val data_penerima_bantuan: List<ModelPenerimaBantuan>? = null
)
