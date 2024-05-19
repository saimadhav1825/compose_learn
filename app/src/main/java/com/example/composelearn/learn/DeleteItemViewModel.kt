package com.example.composelearn.learn

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DeleteItemViewModel @Inject constructor(

) :ViewModel(){
    private val _deleteState = MutableStateFlow(DeleteState())
    val deleteState: StateFlow<DeleteState> = _deleteState.asStateFlow()

    fun handleDeleteEvent(deleteStateEvent: DeleteStateEvent) {
        when (deleteStateEvent) {
            is DeleteStateEvent.AddItem -> {
                _deleteState.update {
                    it.copy(list = it.list.plus(deleteStateEvent.index))
                }

            }

            is DeleteStateEvent.DeleteItem -> {
                _deleteState.update {
                    it.copy(list = it.list.minus(deleteStateEvent.index))
                }
            }
        }
    }
}

data class DeleteState(
    val list: List<String> = emptyList()
)

sealed class DeleteStateEvent() {
    data class DeleteItem(val index: String) : DeleteStateEvent()
    data class AddItem(val index: String) : DeleteStateEvent()
}