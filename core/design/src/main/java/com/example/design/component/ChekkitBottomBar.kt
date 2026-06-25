package com.example.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.design.theme.ChekkitTheme

@Composable
fun ChekkitBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    onCameraClick: () -> Unit
) {
    val dimen = ChekkitTheme.dimensions
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            containerColor = colorScheme.surface,
            tonalElevation = dimen.paddingSmall
        ) {
            val itemColors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorScheme.primary,
                selectedTextColor = colorScheme.primary,
                unselectedIconColor = colorScheme.onSurfaceVariant,
                unselectedTextColor = colorScheme.onSurfaceVariant,
                indicatorColor = Color.Transparent
            )

            NavigationBarItem(
                selected = selectedIndex == 0,
                onClick = { onItemSelected(0) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                colors = itemColors
            )
            NavigationBarItem(
                selected = selectedIndex == 1,
                onClick = { onItemSelected(1) },
                icon = { Icon(Icons.Default.List, contentDescription = "Receipts") },
                label = { Text("Receipts") },
                colors = itemColors
            )
            
            Spacer(modifier = Modifier.weight(1f))

            NavigationBarItem(
                selected = selectedIndex == 2,
                onClick = { onItemSelected(2) },
                icon = { Icon(Icons.Default.PieChart, contentDescription = "Insights") },
                label = { Text("Insights") },
                colors = itemColors
            )
            NavigationBarItem(
                selected = selectedIndex == 3,
                onClick = { onItemSelected(3) },
                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                label = { Text("Settings") },
                colors = itemColors
            )
        }

        // The Center Camera Button
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(y = (-20).dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colorScheme.primary,
                            colorScheme.primaryContainer
                        )
                    )
                )
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = onCameraClick) {
                Icon(
                    imageVector = Icons.Outlined.PhotoCamera,
                    contentDescription = "Camera",
                    tint = colorScheme.onPrimary,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
