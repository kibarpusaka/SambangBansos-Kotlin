package com.example.sambang.domain.beneficiary.repository

import com.example.sambang.data.beneficiary.remote.dto.UpdateBeneficiaryRequest
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow

interface UpdateBeneficiaryRepository {
    suspend fun updateBeneficiary(updateBeneficiaryRequest: UpdateBeneficiaryRequest) : Flow<BaseResult<Unit, Failure>>
}