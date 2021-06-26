package com.example.sambang

import com.google.gson.annotations.SerializedName

data class ResultSimple (

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
        )