package com.example.data.repository

import com.example.domain.model.CategorySummary
import com.example.domain.model.DashboardData
import com.example.domain.model.RecentScan
import com.example.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DashboardRepositoryImpl : DashboardRepository {
    override fun getDashboardData(): Flow<DashboardData> {
        return flowOf(
            DashboardData(
                userName = "Alex",
                totalSpent = 1248.50,
                spentMonth = "Oct",
                percentageChange = -12.0,
                categorySummaries = listOf(
                    CategorySummary("Food", 420.00, "#3B82F6"),
                    CategorySummary("Shopping", 315.20, "#8B5CF6"),
                    CategorySummary("Transport", 150.00, "#10B981")
                ),
                recentScans = listOf(
                    RecentScan("1", "Starbucks", "Today, 08:42 AM", 4.50, "Food & Drink"),
                    RecentScan("2", "Shell Station", "Yesterday", 45.00, "Transport")
                )
            )
        )
    }
}
