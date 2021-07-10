package com.example.sambang.Login.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelLogin (

    @field:SerializedName("user_id")
    var user_id: Int? = null,

    @field:SerializedName("username")
    var username: String? = null,

    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("password")
    var password: String? = null



    ) : Serializable