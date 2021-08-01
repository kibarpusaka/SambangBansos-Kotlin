package com.example.sambang.Dashboard.Kependudukan.Keluarga.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelKeluarga(

    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("NomerKK")
    @Expose
    var nomerkk: String? = null,

    @SerializedName("Alamat")
    @Expose
    var alamat: String? = null,

    @SerializedName("Rt")
    @Expose
    var rt: String? = null,

    @SerializedName("Rw")
    @Expose
    var rw: String? = null,

    @SerializedName("Desa")
    @Expose
    var desa: String? = null,

    var namaDesa: String = ""

) : Serializable {
    override fun toString(): String {
        return nomerkk.toString()
    }
}