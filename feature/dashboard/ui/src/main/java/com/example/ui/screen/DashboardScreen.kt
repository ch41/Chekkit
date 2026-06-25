package com.example.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.design.theme.ChekkitTheme
import com.example.domain.model.DashboardData
import com.example.domain.model.RecentScan
import com.example.ui.model.DashboardEffect
import com.example.ui.model.DashboardState
import com.example.ui.viewModel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                DashboardEffect.NavigateToScanner -> {}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (val currentState = state) {
            is DashboardState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            is DashboardState.Success -> {
                DashboardContent(currentState.data)
            }
            is DashboardState.Error -> {
                Text(
                    text = currentState.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {}
        }
    }
}

@Composable
fun DashboardContent(data: DashboardData) {
    val dimen = ChekkitTheme.dimensions
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimen.screenPadding),
        contentPadding = PaddingValues(top = dimen.paddingLarge, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(dimen.paddingLarge)
    ) {
        item {
            HeaderSection(data.userName)
        }
        item {
            TotalSpentCard(data.totalSpent, data.spentMonth, data.percentageChange)
        }
        item {
            SpendBreakdownSection(data)
        }
        item {
            RecentScansSection(data.recentScans)
        }
    }
}

@Composable
fun HeaderSection(userName: String) {
    val dimen = ChekkitTheme.dimensions
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(dimen.avatarSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.width(dimen.paddingMedium))
            Column {
                Text(
                    text = "Good morning,",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = dimen.fontBody.value.sp
                )
                Text(
                    text = userName,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimen.fontHeader.value.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun TotalSpentCard(total: Double, month: String, change: Double) {
    val dimen = ChekkitTheme.dimensions
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimen.cardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(dimen.paddingLarge)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Spent ($month)",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = dimen.fontBody.value.sp
                )
                Surface(
                    shape = RoundedCornerShape(dimen.paddingSmall),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.TrendingDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(dimen.iconSizeSmall)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${change.toInt()}%",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = dimen.fontCaption.value.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimen.paddingSmall))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimen.fontHeader.value.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = String.format("%,d", total.toInt()),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimen.fontDisplay.value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = ".${(total % 1 * 100).toInt()}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = dimen.fontHeader.value.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(dimen.paddingLarge))
            Row(horizontalArrangement = Arrangement.spacedBy(dimen.paddingMedium)) {
                SmallCategoryCard("Food", "$420.00", Color(0xFF3B82F6))
                SmallCategoryCard("Shopping", "$315.20", Color(0xFF8B5CF6))
            }
        }
    }
}

@Composable
fun SmallCategoryCard(name: String, amount: String, color: Color) {
    val dimen = ChekkitTheme.dimensions
    Surface(
        shape = RoundedCornerShape(dimen.paddingMedium),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier.width(140.dp)
    ) {
        Column(modifier = Modifier.padding(dimen.paddingSmall)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(dimen.paddingSmall))
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = dimen.fontCaption.value.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = amount,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = dimen.fontBody.value.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SpendBreakdownSection(data: DashboardData) {
    val dimen = ChekkitTheme.dimensions
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Spend Breakdown",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = dimen.fontTitle.value.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Details",
                color = MaterialTheme.colorScheme.primary,
                fontSize = dimen.fontBody.value.sp
            )
        }
        Spacer(modifier = Modifier.height(dimen.paddingMedium))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimen.cardCornerRadius),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                DonutChart(data)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$1.2k",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = dimen.fontHeader.value.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun DonutChart(data: DashboardData) {
    Canvas(modifier = Modifier.size(140.dp)) {
        val strokeWidth = 15.dp.toPx()
        // Using fixed colors for the donut segments to match the specific aesthetic
        drawArc(
            color = Color(0xFF10B981), // Emerald
            startAngle = -90f,
            sweepAngle = 120f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        drawArc(
            color = Color(0xFF3B82F6), // Blue
            startAngle = 30f,
            sweepAngle = 100f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        drawArc(
            color = Color(0xFF8B5CF6), // Purple
            startAngle = 130f,
            sweepAngle = 140f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun RecentScansSection(scans: List<RecentScan>) {
    val dimen = ChekkitTheme.dimensions
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Scans",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = dimen.fontTitle.value.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "See All",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = dimen.fontBody.value.sp
            )
        }
        Spacer(modifier = Modifier.height(dimen.paddingMedium))
        Column(verticalArrangement = Arrangement.spacedBy(dimen.paddingSmall)) {
            scans.forEach { scan ->
                RecentScanItem(scan)
            }
        }
    }
}

@Composable
fun RecentScanItem(scan: RecentScan) {
    val dimen = ChekkitTheme.dimensions
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(dimen.paddingMedium)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                val icon = when(scan.category) {
                    "Food & Drink" -> "☕"
                    "Transport" -> "⛽"
                    else -> "📄"
                }
                Text(text = icon, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(dimen.paddingMedium))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = scan.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimen.fontBody.value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = scan.date,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = dimen.fontCaption.value.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${scan.amount}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimen.fontBody.value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = scan.category,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = dimen.fontCaption.value.sp
                )
            }
        }
    }
}
