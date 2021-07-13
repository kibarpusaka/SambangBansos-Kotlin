package com.example.sambang.ui.beneficiary

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.ui.add_or_edit_beneficiary.CreateOrUpdateBeneficiaryActivity
import com.example.sambang.databinding.ActivityPenerimaBantuanBinding
import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity
import com.example.sambang.ui.common.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BeneficiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPenerimaBantuanBinding

    private val viewModel : BeneficiaryActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPenerimaBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observe()
        fetchBeneficiaries()
    }

    private fun fetchBeneficiaries(){
        viewModel.fetchBeneficiaries()
    }

    private fun observe(){
        observeState()
        observeBeneficiaries()
    }

    private fun observeState(){
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeBeneficiaries(){
        viewModel.beneficiaries.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleBeneficiaries(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: BeneficiaryState){
        when(state){
            is BeneficiaryState.IsLoading -> handleLoading(state.isLoading)
            is BeneficiaryState.ShowToast -> makeToast(state.message)
            is BeneficiaryState.Init -> Unit
        }
    }

    private fun handleLoading(isLoading: Boolean){
        binding.loadingBar.isIndeterminate = isLoading
        if(!isLoading){
            binding.loadingBar.progress = 0
        }
    }

    private fun handleBeneficiaries(beneficiaries : List<BeneficiaryEntity>){
        binding.rvPenerimaBantuanBantuan.adapter?.let { adapter ->
            if(adapter is AdapterPenerimaBantuan){
                adapter.update(beneficiaries)
            }
        }
    }

    private fun deleteBeneficiary(beneficiary: BeneficiaryEntity){
        AlertDialog.Builder(this).apply {
            setMessage("Hapus penerima bantuan ini?")
            setPositiveButton("Hapus"){ d, _ ->
                viewModel.deleteBeneficiary(beneficiary)
                d.dismiss()
            }
            setNegativeButton("Batal"){d, _ ->
                d.cancel()
            }
        }.show()
    }

    private fun setupRecyclerView(){
        val mAdapter = AdapterPenerimaBantuan(mutableListOf())
        mAdapter.setAdapterListener(object : AdapterPenerimaBantuan.AdapterListener{
            override fun onTapEdit(beneficiary: BeneficiaryEntity) = goToEditPage(beneficiary)
            override fun onTapDelete(beneficiary: BeneficiaryEntity) = deleteBeneficiary(beneficiary)
        })

        binding.rvPenerimaBantuanBantuan.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@BeneficiaryActivity)
        }
    }

    private fun goToEditPage(beneficiary: BeneficiaryEntity){
        startActivity(Intent(this@BeneficiaryActivity, CreateOrUpdateBeneficiaryActivity::class.java).apply {
            putExtra("penerimaBantuan", beneficiary)
        })
    }
}