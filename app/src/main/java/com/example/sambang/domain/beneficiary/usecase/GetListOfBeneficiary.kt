package com.example.sambang.domain.beneficiary.usecase

import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity
import com.example.sambang.domain.beneficiary.repository.BeneficiaryRepository
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListOfBeneficiary @Inject constructor(private val beneficiaryRepository: BeneficiaryRepository) {
    suspend fun invoke() : Flow<BaseResult<List<BeneficiaryEntity>, Failure>> = beneficiaryRepository.getListOfBeneficiary()
}