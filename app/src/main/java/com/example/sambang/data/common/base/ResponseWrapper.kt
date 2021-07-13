package com.example.sambang.data.common.base

import com.google.gson.annotations.SerializedName

class WrappedResponse constructor(
    @SerializedName("message")
    private var message : String? = null,
    @SerializedName("status")
    private var status : Boolean? = null,
    @SerializedName("token")
    private var token : String? = null,

)
