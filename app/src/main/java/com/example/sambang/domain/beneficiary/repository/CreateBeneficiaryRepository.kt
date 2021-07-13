package com.example.sambang.domain.beneficiary.repository

import com.example.sambang.data.beneficiary.remote.dto.CreateBeneficiaryRequest
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow

interface CreateBeneficiaryRepository {
    suspend fun createBeneficiary(createBeneficiaryRequest: CreateBeneficiaryRequest) : Flow<BaseResult<Unit,Failure>>
}