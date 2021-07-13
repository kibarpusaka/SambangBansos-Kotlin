package com.example.sambang.data.proposal

import com.example.sambang.data.proposal.remote.api.ProposalApi
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import com.example.sambang.domain.proposal.entity.ResidentInformationEntity
import com.example.sambang.domain.proposal.repository.ProposalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProposalRepositoryImpl constructor(private val proposalApi: ProposalApi) : ProposalRepository {
    override suspend fun getListProposal(): Flow<BaseResult<List<ResidentInformationEntity>, Failure>> {
        return flow {
            val response = proposalApi.getListOfProposal()
            if(response.isSuccessful){
                val body = response.body()!!
                if (body.status){
                    val proposals = mutableListOf<ResidentInformationEntity>()
                    var residentInfoEntity : ResidentInformationEntity? = null
                    body.listOfProposal.forEach { d ->
                        residentInfoEntity = ResidentInformationEntity(
                            d.id, d.nik, d.name, d.placeOfBirth, d.dateOfBirth, d.address,
                            d.rt, d.rw, d.isNikValid, d.villageId, d.familyId
                        )
                        proposals.add(residentInfoEntity!!)
                    }
                    emit(BaseResult.Success(proposals))
                }else{
                    emit(BaseResult.Error(Failure(response.code(), body.message)))
                }
            }else{
                val message = "Tidak dapat mengambil daftar usulan."
                emit(BaseResult.Error(Failure(response.code(), message)))
            }
        }
    }
}