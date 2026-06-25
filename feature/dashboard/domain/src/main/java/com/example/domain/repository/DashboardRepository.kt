package com.example.domain.repository

import com.example.domain.model.DashboardData
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getDashboardData(): Flow<DashboardData>
}
