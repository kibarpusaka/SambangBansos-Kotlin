package com.example.sambang.domain.proposal.usecase

import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import com.example.sambang.domain.proposal.entity.ResidentInformationEntity
import com.example.sambang.domain.proposal.repository.ProposalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListOfProposalUseCase @Inject constructor(private val proposalRepository: ProposalRepository){
    suspend fun invoke() : Flow<BaseResult<List<ResidentInformationEntity>, Failure>> = proposalRepository.getListProposal()
}