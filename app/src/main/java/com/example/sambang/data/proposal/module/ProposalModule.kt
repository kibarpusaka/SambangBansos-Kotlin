package com.example.sambang.data.proposal.module

import com.example.sambang.data.proposal.ProposalRepositoryImpl
import com.example.sambang.data.proposal.remote.api.ProposalApi
import com.example.sambang.domain.proposal.repository.ProposalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProposalModule {

    @Provides
    @Singleton
    fun provideProposalApi(retrofit: Retrofit) : ProposalApi {
        return retrofit.create(ProposalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProposalRepository(proposalApi: ProposalApi) : ProposalRepository {
        return ProposalRepositoryImpl(proposalApi)
    }
}