package com.example.ui.model

import com.example.presentation.BaseState

sealed class DashboardState : BaseState {
    object Loading : DashboardState()
    data class Success(val data: List<String>) : DashboardState()
    data class Error(val message: String) : DashboardState()
    object Idle : DashboardState()
}