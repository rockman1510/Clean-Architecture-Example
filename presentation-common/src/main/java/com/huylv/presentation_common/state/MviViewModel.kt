package com.huylv.presentation_common.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MviViewModel<MODEL : Any, STATE : UiState<MODEL>, ACTION : UiAction, EVENT : UiSingleEvent> :
    ViewModel() {

    private val _uiStateFlow: MutableStateFlow<STATE> by lazy { MutableStateFlow(initState()) }
    val uiStateFlow: StateFlow<STATE> = _uiStateFlow
    private val actionFlow: MutableSharedFlow<ACTION> = MutableSharedFlow()
    private val _singleEventFlow = Channel<EVENT>()
    val singleEventFlow = _singleEventFlow.receiveAsFlow()

    abstract fun initState(): STATE

    abstract fun handleAction(action: ACTION)

    init {
        viewModelScope.launch {
            actionFlow.collect {
                handleAction(it)
            }
        }
    }

    fun submitAction(action: ACTION) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    fun submitState(state: STATE) {
        viewModelScope.launch {
            _uiStateFlow.value = state
        }
    }

    fun submitSingleEvent(event: EVENT) {
        viewModelScope.launch {
            _singleEventFlow.send(event)
        }
    }
}