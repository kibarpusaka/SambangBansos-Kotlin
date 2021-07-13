package com.example.sambang.domain.proposal.repository

import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import com.example.sambang.domain.proposal.entity.ResidentInformationEntity
import kotlinx.coroutines.flow.Flow

interface ProposalRepository {
    suspend fun getListProposal() : Flow<BaseResult<List<ResidentInformationEntity>, Failure>>
}