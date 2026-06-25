package com.example.domain.use_case

import com.example.domain.repository.DashboardRepository
import com.example.domain.model.DashboardData
import kotlinx.coroutines.flow.Flow

class GetDashboardDataUseCase(
    private val repository: DashboardRepository
) {
    operator fun invoke(): Flow<DashboardData> = repository.getDashboardData()
}
