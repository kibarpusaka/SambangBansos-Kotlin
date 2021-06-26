package com.example.sambang.Dashboard.Master.Kecamatan.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelKecamatanMaster {

    @SerializedName("id")
    @Expose
    var id : Int? = null

    @SerializedName("Nama")
    @Expose
    var nama : String? = null

    @SerializedName("Kode")
    @Expose
    var kode : Int? = null
}