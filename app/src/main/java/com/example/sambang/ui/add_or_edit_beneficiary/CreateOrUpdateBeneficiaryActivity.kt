package com.example.sambang.ui.add_or_edit_beneficiary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.sambang.R
import com.example.sambang.data.beneficiary.remote.dto.CreateBeneficiaryRequest
import com.example.sambang.data.beneficiary.remote.dto.UpdateBeneficiaryRequest
import com.example.sambang.databinding.ActivityAddPenerimaBantuanBinding
import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity
import com.example.sambang.ui.common.extension.makeAlert
import com.example.sambang.ui.common.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CreateOrUpdateBeneficiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddPenerimaBantuanBinding
    private val viewModel : CreateOrUpdateBeneficiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPenerimaBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLayout()
        observeState()
    }

    private fun observeState(){
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: CreateOrUpdateBeneficiaryState){
        when(state){
            is CreateOrUpdateBeneficiaryState.Success -> {
                makeToast("Berhasil ditambahkan")
                finish()
            }
            is CreateOrUpdateBeneficiaryState.ShowAlert -> makeAlert(state.message)
            is CreateOrUpdateBeneficiaryState.IsLoading -> handleLoading(state.isLoading)
            is CreateOrUpdateBeneficiaryState.Init -> Unit
        }
    }

    private fun handleLoading(b: Boolean){
        binding.btnCreatePenerima.isEnabled = !b
    }

    private fun isShouldUpdate() = intent.getSerializableExtra("penerimaBantuan") != null

    private fun setupLayout(){
        if(isShouldUpdate()){
            setupUpdateLayout()
        }else{
            setupCreateLayout()
        }
    }

    private fun setupCreateLayout(){
        binding.btnCreatePenerima.setOnClickListener { doCreate() }
    }

    private fun getPassedBeneficiary() = intent.getSerializableExtra("penerimaBantuan") as BeneficiaryEntity

    private fun setupUpdateLayout(){
        val beneficiary = getPassedBeneficiary()
        binding.btnCreatePenerima.text = "Simpan"
        binding.etStatusPenerima.setText(beneficiary.status.toString())
        binding.etTanggalPenerima.setText(beneficiary.dateProposed)
        binding.etBantuanPenerima.setText(beneficiary.support.toString())
        binding.etKeluargaPenerima.setText(beneficiary.familyId.toString())
        binding.btnCreatePenerima.setOnClickListener {
            doUpdate()
        }
    }

    private fun doUpdate(){
        val status =  binding.etStatusPenerima.text.toString()
        val dateProposed = binding.etTanggalPenerima.text.toString()
        val support = binding.etBantuanPenerima.text.toString()
        val family =  binding.etKeluargaPenerima.text.toString()
        if(validate(status, dateProposed, support, family)){
            val req = UpdateBeneficiaryRequest(
                getPassedBeneficiary().id,
                status.toInt(),
                dateProposed,
                support.toInt(),
                family.toInt()
            )
            viewModel.update(req)
        }

    }


    private fun validate(status: String, dateProposed: String, support: String, familyId: String) : Boolean{
        if(status.toIntOrNull() == null){
            makeAlert(getString(R.string.validator_status))
            return false
        }

        if(dateProposed.isEmpty()){
            makeAlert(getString(R.string.validator_date_proposed))
            return false
        }

        if(support.toIntOrNull() == null){
            makeAlert(getString(R.string.validator_support))
            return false
        }

        if(familyId.toIntOrNull() == null){
            makeAlert(getString(R.string.validator_family))
            return false
        }

        return true

    }

    private fun doCreate(){
        val status =  binding.etStatusPenerima.text.toString()
        val dateProposed = binding.etTanggalPenerima.text.toString()
        val support = binding.etBantuanPenerima.text.toString()
        val family =  binding.etKeluargaPenerima.text.toString()
        if(validate(status, dateProposed, support, family)){
            val req = CreateBeneficiaryRequest(
                status.toInt(),
                dateProposed,
                support.toInt(),
                family.toInt()
            )
            viewModel.create(req)
        }
    }
}