package com.example.sambang.Dashboard.Master.Desa.Data

import com.example.sambang.Login.Data.ModelLogin
import com.google.gson.annotations.SerializedName

data class ResponDesa (
    @field:SerializedName("status") val status : Boolean,
    @field:SerializedName("message") val message : String,
    @field:SerializedName("data_desa") val data_desa : List<ModelDesaMaster>
        )