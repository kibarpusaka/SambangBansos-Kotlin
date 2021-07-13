package com.example.sambang.data.login.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse  (
    @SerializedName("message")
    var message : String? = null,

    @SerializedName("status")
    var status : Boolean? = null,

    @SerializedName("token")
    var token : String? = null,

    @SerializedName("user")
    var user : LoginUserResponse


)