package com.example.sambang.ui.add_or_edit_beneficiary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambang.data.beneficiary.remote.dto.CreateBeneficiaryRequest
import com.example.sambang.data.beneficiary.remote.dto.UpdateBeneficiaryRequest
import com.example.sambang.domain.beneficiary.usecase.CreateBeneficiaryUseCase
import com.example.sambang.domain.beneficiary.usecase.UpdateBeneficiaryUseCase
import com.example.sambang.domain.common.base.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrUpdateBeneficiaryViewModel @Inject constructor(
    private val createBeneficiaryUseCase: CreateBeneficiaryUseCase,
    private val updateBeneficiaryUseCase: UpdateBeneficiaryUseCase
) : ViewModel(){
    private var _state = MutableStateFlow<CreateOrUpdateBeneficiaryState>(CreateOrUpdateBeneficiaryState.Init)
    val state : StateFlow<CreateOrUpdateBeneficiaryState> get() = _state

    private fun setLoading(b : Boolean){
        _state.value = CreateOrUpdateBeneficiaryState.IsLoading(b)
    }

    private fun showAlert(msg: String){
        _state.value = CreateOrUpdateBeneficiaryState.ShowAlert(msg)
    }

    fun create(createReq: CreateBeneficiaryRequest){
        viewModelScope.launch {
            createBeneficiaryUseCase.invoke(createReq)
                .onStart { setLoading(true) }
                .catch { e ->
                    showAlert(e.message.toString())
                    setLoading(false)
                }
                .collect { res ->
                    setLoading(false)
                    when(res){
                        is BaseResult.Success -> {
                            _state.value = CreateOrUpdateBeneficiaryState.Success
                        }
                        is BaseResult.Error -> {
                            val msg = "${res.e.message} [${res.e.code}]"
                            showAlert(msg)
                        }
                    }
                }
        }
    }

    fun update(updateReq: UpdateBeneficiaryRequest){
        viewModelScope.launch {
            updateBeneficiaryUseCase.invoke(updateReq)
                .onStart { setLoading(true) }
                .catch { e ->
                    showAlert(e.message.toString())
                    setLoading(false)
                }
                .collect { res ->
                    setLoading(false)
                    when(res){
                        is BaseResult.Success -> {
                            _state.value = CreateOrUpdateBeneficiaryState.Success
                        }
                        is BaseResult.Error -> {
                            val msg = "${res.e.message} [${res.e.code}]"
                            showAlert(msg)
                        }
                    }
                }
        }
    }
}

sealed class CreateOrUpdateBeneficiaryState {
    object Init : CreateOrUpdateBeneficiaryState()
    object Success : CreateOrUpdateBeneficiaryState()
    data class IsLoading(val isLoading: Boolean) : CreateOrUpdateBeneficiaryState()
    data class ShowAlert(val message: String) : CreateOrUpdateBeneficiaryState()
}