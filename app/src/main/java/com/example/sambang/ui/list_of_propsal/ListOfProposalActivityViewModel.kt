package com.example.sambang.ui.list_of_propsal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.proposal.entity.ResidentInformationEntity
import com.example.sambang.domain.proposal.usecase.GetListOfProposalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfProposalActivityViewModel @Inject constructor(
    private val getListOfProposalUseCase: GetListOfProposalUseCase
) : ViewModel(){
    private var _proposals = MutableStateFlow<List<ResidentInformationEntity>>(mutableListOf())
    val proposals : StateFlow<List<ResidentInformationEntity>> get() = _proposals

    private var _state = MutableStateFlow<ListOfProposalActivityState>(ListOfProposalActivityState.Init)
    val state : StateFlow<ListOfProposalActivityState> get() = _state

    private fun setLoading(isLoading : Boolean) {
        _state.value = ListOfProposalActivityState.IsLoading(isLoading)
    }

    private fun showToast(msg : String){
        _state.value = ListOfProposalActivityState.ShowToast(msg)
    }

    fun fetchListOfProposal(){
        viewModelScope.launch {
            getListOfProposalUseCase.invoke()
                .onStart { setLoading(true) }
                .catch { e ->
                    setLoading(false)
                    showToast(e.message.toString())
                }
                .collect { res ->
                    setLoading(false)
                    when(res) {
                        is BaseResult.Success -> {
                            _proposals.value = res.data
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

sealed class ListOfProposalActivityState {
    object Init : ListOfProposalActivityState()
    data class IsLoading(val isLoading: Boolean) : ListOfProposalActivityState()
    data class ShowToast(val message: String) : ListOfProposalActivityState()
}