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

@Composable
fun ChekkitBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    onCameraClick: () -> Unit
) {
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
            containerColor = Color(0xFF111D23), // Dark background matching the design
            tonalElevation = 8.dp
        ) {
            NavigationBarItem(
                selected = selectedIndex == 0,
                onClick = { onItemSelected(0) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1DB954),
                    selectedTextColor = Color(0xFF1DB954),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                selected = selectedIndex == 1,
                onClick = { onItemSelected(1) },
                icon = { Icon(Icons.Default.List, contentDescription = "Receipts") },
                label = { Text("Receipts") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1DB954),
                    selectedTextColor = Color(0xFF1DB954),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
            
            // Spacer for the center button
            Spacer(modifier = Modifier.weight(1f))

            NavigationBarItem(
                selected = selectedIndex == 2,
                onClick = { onItemSelected(2) },
                icon = { Icon(Icons.Default.PieChart, contentDescription = "Insights") },
                label = { Text("Insights") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1DB954),
                    selectedTextColor = Color(0xFF1DB954),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                selected = selectedIndex == 3,
                onClick = { onItemSelected(3) },
                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                label = { Text("Settings") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1DB954),
                    selectedTextColor = Color(0xFF1DB954),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }

        // The Center Camera Button (Floating Action Button style)
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(y = (-20).dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1DB954), // Glowy green
                            Color(0xFF004D40)
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
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
