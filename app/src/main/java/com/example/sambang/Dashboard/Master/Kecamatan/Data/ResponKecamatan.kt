package com.example.sambang.Dashboard.Master.Kecamatan.Data

import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.google.gson.annotations.SerializedName

data class ResponKecamatan(
    @field:SerializedName("status") val status : Boolean,
    @field:SerializedName("message") val message : String,
    @field:SerializedName("data_desa") val data_desa : List<ModelKecamatanMaster>
)