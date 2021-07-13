package com.example.sambang.data.beneficiary.remote.dto

import com.google.gson.annotations.SerializedName

data class ParentBeneficiaryResponse(
    @SerializedName("message")
    var message : String,
    @SerializedName("status")
    var status : Boolean,
    @SerializedName("data_penerima_bantuan")
    var beneficiaries : List<BeneficiaryResponse> = mutableListOf()
)