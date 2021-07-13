package com.example.sambang.ui.beneficiary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambang.Utils.Base
import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity
import com.example.sambang.domain.beneficiary.usecase.DeleteBeneficiaryUseCase
import com.example.sambang.domain.beneficiary.usecase.GetListOfBeneficiary
import com.example.sambang.domain.common.base.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeneficiaryActivityViewModel @Inject constructor(
    private val getListOfBeneficiary: GetListOfBeneficiary,
    private val deleteBeneficiaryUseCase: DeleteBeneficiaryUseCase
) : ViewModel(){
    private var _state = MutableStateFlow<BeneficiaryState>(BeneficiaryState.Init)
    val state : StateFlow<BeneficiaryState> get() = _state

    private var _beneficiaries = MutableStateFlow<List<BeneficiaryEntity>>(mutableListOf())
    val beneficiaries : StateFlow<List<BeneficiaryEntity>> get() = _beneficiaries

    private fun setLoading(b: Boolean) {
        _state.value = BeneficiaryState.IsLoading(b)
    }

    private fun showToast(msg: String){
        _state.value = BeneficiaryState.ShowToast(msg)
    }

    fun deleteBeneficiary(beneficiaryEntity: BeneficiaryEntity){
        viewModelScope.launch {
            deleteBeneficiaryUseCase.invoke(beneficiaryEntity.id.toString())
                .onStart { setLoading(true) }
                .catch { e ->
                    setLoading(false)
                    showToast(e.message.toString())
                }
                .collect { res ->
                    setLoading(false)
                    when(res){
                        is BaseResult.Success -> {
                            showToast("Berhasil dihapus")
                            fetchBeneficiaries()
                        }
                        is BaseResult.Error -> {
                            val msg = "Gagal menghapus karena: ${res.e.message} [${res.e.code}]"
                            showToast(msg)
                        }
                    }
                }
        }
    }

    fun fetchBeneficiaries(){
        viewModelScope.launch {
            getListOfBeneficiary.invoke()
                .onStart { setLoading(true) }
                .catch { e ->
                    setLoading(false)
                    showToast(e.message.toString())
                }
                .collect { res ->
                    setLoading(false)
                    when(res){
                        is BaseResult.Success -> {
                            _beneficiaries.value = res.data
                        }
                        is BaseResult.Error -> {
                            val msg = "${res.e.message} [${res.e.code}]"
                            showToast(msg)
                        }
                    }
                }
        }
    }
}


sealed class BeneficiaryState {
    object Init : BeneficiaryState()
    data class IsLoading(val isLoading: Boolean) : BeneficiaryState()
    data class ShowToast(val message : String) : BeneficiaryState()
}