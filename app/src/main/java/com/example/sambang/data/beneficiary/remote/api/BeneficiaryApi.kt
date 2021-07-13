package com.example.sambang.data.beneficiary.remote.api

import com.example.sambang.data.beneficiary.remote.dto.CreateBeneficiaryRequest
import com.example.sambang.data.beneficiary.remote.dto.ParentBeneficiaryResponse
import com.example.sambang.data.beneficiary.remote.dto.UpdateBeneficiaryRequest
import com.example.sambang.data.common.base.ResponseSimple
import retrofit2.Response
import retrofit2.http.*

interface BeneficiaryApi {
    @GET("/api/penerimabantuan/")
    suspend fun getListOfBeneficiary() : Response<ParentBeneficiaryResponse>

    @DELETE("/api/penerimabantuan/{id}/")
    suspend fun deleteBeneficiary(@Path("id") id : String) : Response<ResponseSimple>

    @POST("/api/penerimabantuan/")
    suspend fun createBeneficiary(createBeneficiaryRequest: CreateBeneficiaryRequest) : Response<ResponseSimple>

    @PUT("/api/penerimabantuan/{id}/")
    suspend fun updateBeneficiary(@Path("id") id: String, updateBeneficiaryRequest: UpdateBeneficiaryRequest) : Response<ResponseSimple>
}