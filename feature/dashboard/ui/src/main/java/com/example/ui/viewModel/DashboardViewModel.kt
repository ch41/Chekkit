package com.example.ui.viewModel

import com.example.presentation.BaseViewModel
import com.example.ui.model.DashboardEffect
import com.example.ui.model.DashboardIntent
import com.example.ui.model.DashboardState

class DashboardViewModel : BaseViewModel<DashboardState, DashboardIntent, DashboardEffect>() {

    override fun createInitialState(): DashboardState = DashboardState.Loading

    override suspend fun handleEvent(event: DashboardIntent) {

        when (event) {
            DashboardIntent.LoadData -> {}
            is DashboardIntent.OnItemClicked -> {}
        }
    }

}