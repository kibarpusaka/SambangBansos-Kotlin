package com.example.sambang.data.beneficiary.module

import com.example.sambang.data.beneficiary.BeneficiaryRepositoryImpl
import com.example.sambang.data.beneficiary.remote.api.BeneficiaryApi
import com.example.sambang.domain.beneficiary.repository.BeneficiaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BeneficiaryModule {
    @Provides
    @Singleton
    fun provideBeneficiaryApi(retrofit: Retrofit) : BeneficiaryApi {
        return retrofit.create(BeneficiaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeneficiaryRepository(beneficiaryApi: BeneficiaryApi) : BeneficiaryRepository {
        return BeneficiaryRepositoryImpl(beneficiaryApi)
    }
}