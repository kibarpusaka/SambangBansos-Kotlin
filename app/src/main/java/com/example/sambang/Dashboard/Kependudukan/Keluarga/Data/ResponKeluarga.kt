package com.example.sambang.Dashboard.Kependudukan.Keluarga.Data

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.google.gson.annotations.SerializedName

data class ResponKeluarga(

    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data_keluarga")
    val data_keluarga: List<ModelKeluarga>? = null
)
