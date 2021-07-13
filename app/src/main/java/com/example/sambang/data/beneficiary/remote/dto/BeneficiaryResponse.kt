package com.example.sambang.data.beneficiary.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BeneficiaryResponse(
    @SerializedName("id")
    var id : Int,

    @SerializedName("Status")
    var status : Int,

    @SerializedName("TglPengajuan")
    var dateProposed : String,

    @SerializedName("Bantuan")
    var support : Int,

    @SerializedName("Keluarga")
    var familyId : Int
)