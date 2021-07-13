package com.example.sambang.data.beneficiary

import com.example.sambang.data.beneficiary.remote.api.BeneficiaryApi
import com.example.sambang.data.beneficiary.remote.dto.CreateBeneficiaryRequest
import com.example.sambang.data.beneficiary.remote.dto.UpdateBeneficiaryRequest
import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity
import com.example.sambang.domain.beneficiary.repository.BeneficiaryRepository
import com.example.sambang.domain.beneficiary.repository.CreateBeneficiaryRepository
import com.example.sambang.domain.beneficiary.repository.UpdateBeneficiaryRepository
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BeneficiaryRepositoryImpl constructor(
    private val beneficiaryApi: BeneficiaryApi
) : BeneficiaryRepository, CreateBeneficiaryRepository, UpdateBeneficiaryRepository {

    override suspend fun deleteBeneficiary(id: String): Flow<BaseResult<Unit, Failure>> {
        return flow {
            val response = beneficiaryApi.deleteBeneficiary(id)
            if(response.isSuccessful){
                val body = response.body()!!
                if (body.status){
                    emit(BaseResult.Success(Unit))
                }else{
                    emit(BaseResult.Error(Failure(response.code(), body.message)))
                }
            }else{
                emit(BaseResult.Error(Failure(response.code(), response.message())))
            }
        }
    }

    override suspend fun getListOfBeneficiary(): Flow<BaseResult<List<BeneficiaryEntity>, Failure>> {
        return flow {
            val response = beneficiaryApi.getListOfBeneficiary()
            if (response.isSuccessful){
                val body = response.body()!!
                if (body.status){
                    val beneficiaries = mutableListOf<BeneficiaryEntity>()
                    var temp: BeneficiaryEntity?
                    body.beneficiaries.forEach { b ->
                        temp = BeneficiaryEntity(b.id, b.status, b.dateProposed, b.support, b.familyId)
                        beneficiaries.add(temp!!)
                    }
                    emit(BaseResult.Success(beneficiaries))
                }else{
                    emit(BaseResult.Error(Failure(response.code(), body.message)))
                }
            }else{
                emit(BaseResult.Error(Failure(response.code(), response.message())))
            }
        }
    }

    override suspend fun createBeneficiary(createBeneficiaryRequest: CreateBeneficiaryRequest): Flow<BaseResult<Unit, Failure>> {
        return flow {
            val response = beneficiaryApi.createBeneficiary(createBeneficiaryRequest)
            if(response.isSuccessful){
                val body = response.body()!!
                if (body.status){
                    emit(BaseResult.Success(Unit))
                }else{
                    emit(BaseResult.Error(Failure(response.code(), body.message)))
                }
            }else{
                emit(BaseResult.Error(Failure(response.code(), response.message())))
            }
        }
    }

    override suspend fun updateBeneficiary(updateBeneficiaryRequest: UpdateBeneficiaryRequest): Flow<BaseResult<Unit, Failure>> {
        return flow {
            val response = beneficiaryApi.updateBeneficiary(updateBeneficiaryRequest.id.toString(), updateBeneficiaryRequest)
            if(response.isSuccessful){
                val body = response.body()!!
                if (body.status){
                    emit(BaseResult.Success(Unit))
                }else{
                    emit(BaseResult.Error(Failure(response.code(), body.message)))
                }
            }else{
                emit(BaseResult.Error(Failure(response.code(), response.message())))
            }
        }
    }
}