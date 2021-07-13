package com.example.sambang.data.proposal.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResidentInformationResponse(
    @SerializedName("id")
    var id : Int,

    @SerializedName("Nik")
    var nik : String,

    @SerializedName("Nama")
    var name : String,

    @SerializedName("TmpLahir")
    var placeOfBirth : String,

    @SerializedName("TglLahir")
    var dateOfBirth : String,

    @SerializedName("Alamat")
    var address : String,

    @SerializedName("Rt")
    var rt : Int = 0,

    @SerializedName("Rw")
    var rw : Int = 0,

    @SerializedName("NikValid")
    var isNikValid : Boolean = false,

    @SerializedName("Desa")
    var villageId : Int ,

    @SerializedName("Keluarga")
    var familyId : Int
)