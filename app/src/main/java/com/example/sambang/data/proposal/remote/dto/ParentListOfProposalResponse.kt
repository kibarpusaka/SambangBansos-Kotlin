package com.example.sambang.data.proposal.remote.dto

import com.google.gson.annotations.SerializedName

data class ParentListOfProposalResponse(
    @SerializedName("status")
    var status : Boolean,

    @SerializedName("message")
    var message : String,

    @SerializedName("data_warga")
    var listOfProposal : List<ResidentInformationResponse> = mutableListOf()
)