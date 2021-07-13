package com.example.sambang.domain.beneficiary.usecase

import com.example.sambang.data.beneficiary.remote.dto.UpdateBeneficiaryRequest
import com.example.sambang.domain.beneficiary.repository.UpdateBeneficiaryRepository
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBeneficiaryUseCase @Inject constructor(
    private val updateBeneficiaryRepository: UpdateBeneficiaryRepository
) {
    suspend fun invoke(updateBeneficiaryRequest: UpdateBeneficiaryRequest) : Flow<BaseResult<Unit, Failure>>{
        return updateBeneficiaryRepository.updateBeneficiary(updateBeneficiaryRequest)
    }
}