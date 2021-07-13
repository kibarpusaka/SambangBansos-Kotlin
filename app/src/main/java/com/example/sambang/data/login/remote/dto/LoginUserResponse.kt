package com.example.sambang.data.login.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginUserResponse (
    @SerializedName("user_id")
    var userId: Int,

    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email : String
)