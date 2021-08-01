package com.example.sambang.Dashboard.Master.Bantuan.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelBantuanMaster(
    @field:SerializedName("id")
    var id : String? = null,

    @field:SerializedName("Nama")
    var nama : String,

    @field:SerializedName("Tahun")
    var tahun : Int? = null,

    @field:SerializedName("Sumber")
    var sumber : String? = null
) : Serializable {
    override fun toString(): String {
        return nama
    }
}
