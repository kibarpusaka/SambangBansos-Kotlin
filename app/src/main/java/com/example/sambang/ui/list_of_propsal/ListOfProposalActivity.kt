package com.example.sambang.ui.list_of_propsal

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.databinding.ActivityDaftarUsulanBinding
import com.example.sambang.domain.proposal.entity.ResidentInformationEntity
import com.example.sambang.ui.common.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListOfProposalActivity : AppCompatActivity(){
    private lateinit var binding : ActivityDaftarUsulanBinding

    private val viewModel : ListOfProposalActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarUsulanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        fetchProposals()
    }

    private fun fetchProposals(){
        viewModel.fetchListOfProposal()
    }


    private fun observe(){
        observeState()
        observeProposals()
    }

    private fun observeState(){
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeProposals(){
        viewModel.proposals.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleProposals(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state : ListOfProposalActivityState){
        when(state){
            is ListOfProposalActivityState.Init -> Unit
            is ListOfProposalActivityState.ShowToast -> makeToast(state.message)
            is ListOfProposalActivityState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleLoading(b : Boolean){
        binding.loadingBar.isIndeterminate = b
        if(!b){
            binding.loadingBar.progress = 0
        }
    }

    private fun handleProposals(proposals: List<ResidentInformationEntity>){
        binding.rvDaftarUsulan.adapter?.let { adapter ->
            if(adapter is AdapterDaftarUsulan){
                adapter.update(proposals)
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvDaftarUsulan.apply {
            adapter = AdapterDaftarUsulan(mutableListOf())
            layoutManager = LinearLayoutManager(this@ListOfProposalActivity)
        }
    }
}