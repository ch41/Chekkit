package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Intent : Any, Effect : Any> : ViewModel() {

    private val _state: MutableStateFlow<State>
    val state: StateFlow<State>
        get() = _state

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    init {
        val initialState = createInitialState()
        _state = MutableStateFlow(initialState)
    }

    /**
     * Создать начальное состояние ViewModel.
     */
    protected abstract fun createInitialState(): State

    /**
     * Обработка событий (намерений) от UI.
     */
    protected abstract suspend fun handleEvent(event: Intent)

    /**
     * Отправить событие из UI.
     */
    fun sendEvent(event: Intent) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    /**
     * Обновить состояние.
     */
    protected fun setState(reducer: State.() -> State) {
        _state.update { reducer(it) }
    }

    /**
     * Отправить эффект (одноразовое событие).
     */
    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    /**
     * Получить текущее состояние.
     */
    protected fun currentState(): State = _state.value
}