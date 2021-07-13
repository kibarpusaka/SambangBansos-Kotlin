package com.example.sambang.domain.beneficiary.repository

import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow

interface BeneficiaryRepository {
    suspend fun deleteBeneficiary(id : String) : Flow<BaseResult<Unit, Failure>>
    suspend fun getListOfBeneficiary() : Flow<BaseResult<List<BeneficiaryEntity>, Failure>>
}