package com.example.sambang.Dashboard.Master.Bantuan.Data

import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.google.gson.annotations.SerializedName

data class ResponBantuanMaster(
    @field:SerializedName("status") val status : Boolean,
    @field:SerializedName("message") val message : String,
    @field:SerializedName("data") val data : List<ModelBantuanMaster>
)
