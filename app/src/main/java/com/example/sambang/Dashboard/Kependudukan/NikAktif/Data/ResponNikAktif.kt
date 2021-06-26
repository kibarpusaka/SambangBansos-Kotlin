package com.example.sambang.Dashboard.Kependudukan.NikAktif.Data

import com.google.gson.annotations.SerializedName

data class ResponNikAktif(

    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("nikaktif")
    val nikaktif: List<ModelNikAktif>? = null

)
