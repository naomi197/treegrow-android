package com.treegrow.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MapOutlined
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MapOutlined
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.treegrow.app.ui.theme.PrimaryGreen

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
)

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    val items = listOf(
        BottomNavItem(
            label = "خانه",
            icon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "نقشه",
            icon = Icons.Outlined.MapOutlined,
            selectedIcon = Icons.Filled.MapOutlined,
            route = "map"
        ),
        BottomNavItem(
            label = "پروفایل",
            icon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            route = "profile"
        )
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = PrimaryGreen
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.selectedIcon else item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    indicatorColor = PrimaryGreen.copy(alpha = 0.1f),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
