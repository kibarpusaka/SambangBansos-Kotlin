package com.example.sambang.Login.Data

import com.google.gson.annotations.SerializedName

data class ResponLogin (

    @field:SerializedName("status") val status : Boolean,
    @field:SerializedName("message") val message : String,
    @field:SerializedName("token") val token : String,
    @field:SerializedName("user") val user : ModelLogin

    )

