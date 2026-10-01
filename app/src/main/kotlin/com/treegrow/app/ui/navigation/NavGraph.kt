package com.treegrow.app.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.treegrow.app.ui.components.BottomNavBar
import com.treegrow.app.ui.screens.*

@Composable
fun TreeGrowNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val showBottomNav = remember(currentRoute) {
        currentRoute in listOf("home", "map", "profile")
    }

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                BottomNavBar(navController, currentRoute)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("map") {
                MapScreen(navController)
            }
            composable("leaderboard") {
                LeaderboardScreen(navController)
            }
            composable("achievements") {
                AchievementsScreen(navController)
            }
            composable("challenges") {
                ChallengesScreen(navController)
            }
            composable("profile") {
                ProfileScreen(navController)
            }
        }
    }
}
