package com.example.sambang.Dashboard.Kependudukan.NikAktif.Data

import com.google.gson.annotations.SerializedName

data class ResponNikAktif(

    @field:SerializedName("status") val status: Boolean,

    @field:SerializedName("message") val message: String,

    @field:SerializedName("data_warga") val data_warga: List<ModelNikAktif>

)
