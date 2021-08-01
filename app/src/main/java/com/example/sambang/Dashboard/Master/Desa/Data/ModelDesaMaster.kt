package com.example.sambang.Dashboard.Master.Desa.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelDesaMaster (
    @field:SerializedName("id")
    var id : String,

    @field:SerializedName("Nama")
    var nama : String,

    @field:SerializedName("Kode")
    var kode : String,

    @field:SerializedName("Kecamatan")
    var kecamatan : Int
) : Serializable {
    override fun toString(): String {
        return nama
    }
}