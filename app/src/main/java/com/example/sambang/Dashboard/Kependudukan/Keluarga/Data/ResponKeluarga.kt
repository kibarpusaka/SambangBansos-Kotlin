package com.example.sambang.Dashboard.Kependudukan.Keluarga

import com.google.gson.annotations.SerializedName

data class ResponKeluarga(

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val barang: List<ModelKeluarga>? = null
)
