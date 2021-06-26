package com.example.sambang.Login.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelLogin (

//    @SerializedName("user_id") val user_id : Int,
//    @SerializedName("username") val username : String,
//    @SerializedName("email") val email : String,
//    @SerializedName("token") val token : String

    @field:SerializedName("token")
    var token: String? = null,

    @field:SerializedName("user_id")
    var user_id: Int? = null,

    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("username")
    var username: String? = null,

    @field:SerializedName("password")
    var password: String? = null

    ): Serializable