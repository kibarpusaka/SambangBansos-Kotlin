package com.example.sambang.data.proposal.remote.api

import com.example.sambang.data.proposal.remote.dto.ParentListOfProposalResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProposalApi {
    @GET("/api/warga/")
    suspend fun getListOfProposal() : Response<ParentListOfProposalResponse>
}