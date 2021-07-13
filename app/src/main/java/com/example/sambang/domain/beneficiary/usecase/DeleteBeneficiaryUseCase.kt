package com.example.sambang.domain.beneficiary.usecase

import com.example.sambang.domain.beneficiary.repository.BeneficiaryRepository
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteBeneficiaryUseCase @Inject constructor(private val beneficiaryRepository: BeneficiaryRepository) {
    suspend fun invoke(id: String) : Flow<BaseResult<Unit, Failure>> {
        return beneficiaryRepository.deleteBeneficiary(id)
    }
}