package com.example.composelearn.learn

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SideEffectsViewModel @Inject constructor(

) : ViewModel() {
    private val _sideEffectStateState = MutableStateFlow(SideEffectState())
    val sideEffectStateState: StateFlow<SideEffectState> = _sideEffectStateState.asStateFlow()

    fun handleDeleteEvent(sideEffectEvent: SideEffectEvent) {
        when (sideEffectEvent) {
            is SideEffectEvent.AddItem -> {
                _sideEffectStateState.update {
                    it.copy(list = it.list.plus(sideEffectEvent.index))
                }

            }

            is SideEffectEvent.DeleteItem -> {
                _sideEffectStateState.update {
                    it.copy(list = it.list.minus(sideEffectEvent.index))
                }
            }
        }
    }
}

data class SideEffectState(
    val list: List<String> = emptyList()
)

sealed class SideEffectEvent {
    data class DeleteItem(val index: String) : SideEffectEvent()
    data class AddItem(val index: String) : SideEffectEvent()
}