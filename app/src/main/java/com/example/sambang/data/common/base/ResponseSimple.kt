package com.example.sambang.data.common.base

import com.google.gson.annotations.SerializedName

data class ResponseSimple(
    @SerializedName("message")
    var message : String,

    @SerializedName("status")
    var status : Boolean
)