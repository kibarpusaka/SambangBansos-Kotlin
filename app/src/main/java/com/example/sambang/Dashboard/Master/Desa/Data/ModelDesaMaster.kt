package com.example.sambang.Dashboard.Master.Desa.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelDesaMaster (
    @field:SerializedName("id")
    var id : Int? = null,

    @field:SerializedName("Nama")
    var nama : String? = null,

    @field:SerializedName("Kode")
    var kode : String? = null,

    @field:SerializedName("Kecamatan")
    var kecamatan : Int? = null
) : Serializable