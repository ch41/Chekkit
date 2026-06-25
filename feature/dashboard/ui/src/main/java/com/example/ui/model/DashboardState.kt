package com.example.ui.model

import com.example.domain.model.DashboardData
import com.example.presentation.BaseState

sealed class DashboardState : BaseState {
    data object Loading : DashboardState()
    data class Success(val data: DashboardData) : DashboardState()
    data class Error(val message: String) : DashboardState()
    data object Idle : DashboardState()
}
