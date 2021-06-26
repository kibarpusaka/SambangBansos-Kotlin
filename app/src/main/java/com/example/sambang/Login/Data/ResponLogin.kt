package com.example.sambang.Login.Data

import com.google.gson.annotations.SerializedName

data class ResponLogin (
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : Boolean,
    @SerializedName("data") val user : ModelLogin
        )

//    @field:SerializedName("status")
//    val status: Boolean? = null
//
//    @field:SerializedName("message")
//    val message: String? = null
//
//    @field:SerializedName("user")
//    val user: ModelLogin? = null

