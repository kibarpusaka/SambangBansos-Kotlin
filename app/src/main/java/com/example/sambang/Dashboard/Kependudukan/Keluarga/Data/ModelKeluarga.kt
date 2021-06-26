package com.example.sambang.Dashboard.Kependudukan.Keluarga.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelKeluarga (

    @SerializedName("id")
    @Expose
    var id : Int? = null,

    @SerializedName("NomerKK")
    @Expose
    var nomerkk : String? = null,

    @SerializedName("Alamat")
    @Expose
    var alamat : String? = null,

    @SerializedName("Rt")
    @Expose
    var rt : Int? = null,

    @SerializedName("Rw")
    @Expose
    var rw : Int? = null,

    @SerializedName("Desa")
    @Expose
    var desa : Int? = null
) : Serializable