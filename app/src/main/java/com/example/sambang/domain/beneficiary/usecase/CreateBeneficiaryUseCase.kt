package com.example.sambang.domain.beneficiary.usecase

import com.example.sambang.data.beneficiary.remote.dto.CreateBeneficiaryRequest
import com.example.sambang.domain.beneficiary.repository.CreateBeneficiaryRepository
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateBeneficiaryUseCase @Inject constructor(private val beneficiaryRepository: CreateBeneficiaryRepository) {
    suspend fun invoke(createBeneficiaryRequest: CreateBeneficiaryRequest) : Flow<BaseResult<Unit, Failure>> {
        return beneficiaryRepository.createBeneficiary(createBeneficiaryRequest)
    }
}