package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data

import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.google.gson.annotations.SerializedName

data class ResponDaftarUsulan(
    @field:SerializedName("status") val status: Boolean,

    @field:SerializedName("message") val message: String,

    @field:SerializedName("data_warga") val data_warga: List<ModelDaftarUsulan>
    )