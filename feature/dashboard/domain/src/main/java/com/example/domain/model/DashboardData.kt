package com.example.domain.model

data class DashboardData(
    val userName: String,
    val totalSpent: Double,
    val spentMonth: String,
    val percentageChange: Double,
    val categorySummaries: List<CategorySummary>,
    val recentScans: List<RecentScan>
)

data class CategorySummary(
    val name: String,
    val amount: Double,
    val color: String // Hex color
)

data class RecentScan(
    val id: String,
    val title: String,
    val date: String,
    val amount: Double,
    val category: String,
    val iconRes: String? = null
)
