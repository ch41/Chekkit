package com.example.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.GetDashboardDataUseCase
import com.example.presentation.BaseViewModel
import com.example.ui.model.DashboardEffect
import com.example.ui.model.DashboardIntent
import com.example.ui.model.DashboardState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : BaseViewModel<DashboardState, DashboardIntent, DashboardEffect>() {

    init {
        sendIntent(DashboardIntent.LoadData)
    }

    override fun createInitialState(): DashboardState = DashboardState.Loading

    override suspend fun handleEvent(event: DashboardIntent) {
        when (event) {
            DashboardIntent.LoadData -> loadDashboardData()
            is DashboardIntent.OnItemClicked -> {}
        }
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            getDashboardDataUseCase()
                .onStart { setState { DashboardState.Loading } }
                .catch { e -> setState { DashboardState.Error(e.message ?: "Unknown error") } }
                .collect { data ->
                    setState { DashboardState.Success(data) }
                }
        }
    }
}
