package com.treegrow.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.treegrow.app.ui.theme.PrimaryGreen
import com.treegrow.app.ui.theme.SecondaryGreen
import com.treegrow.app.ui.theme.TertiaryGreen

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "🌳 TreeGrow",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = PrimaryGreen,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Text(
            text = "Plant trees, save the world",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Stats Cards
        StatsSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Tree Display
        TreeDisplaySection()

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        ActionsSection(navController)

        Spacer(modifier = Modifier.height(32.dp))
    }

    // Floating Action Button
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { /* Plant new tree */ },
            containerColor = PrimaryGreen,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Plant Tree")
        }
    }
}

@Composable
fun StatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            title = "My Trees",
            value = "24",
            icon = "🌱",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            title = "Points",
            value = "1,240",
            icon = "⭐",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            title = "Achievements",
            value = "8",
            icon = "🏆",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = TertiaryGreen
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TreeDisplaySection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryGreen.copy(alpha = 0.1f)
        ),
        border = BorderStroke(2.dp, SecondaryGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🌳",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Your Next Tree",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Complete 5 green activities",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
            LinearProgressIndicator(
                progress = { 0.6f },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(8.dp)
                    .padding(top = 16.dp),
                color = PrimaryGreen,
                trackColor = PrimaryGreen.copy(alpha = 0.2f)
            )
            Text(
                text = "3 of 5",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp),
                color = PrimaryGreen
            )
        }
    }
}

@Composable
fun ActionsSection(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ActionButton(
            title = "World Tree Map",
            icon = "🗺️",
            onClick = { navController.navigate("map") }
        )
        ActionButton(
            title = "Social Challenges",
            icon = "👥",
            onClick = { navController.navigate("challenges") }
        )
        ActionButton(
            title = "My Achievements",
            icon = "🏅",
            onClick = { navController.navigate("achievements") }
        )
        ActionButton(
            title = "Leaderboard",
            icon = "📊",
            onClick = { navController.navigate("leaderboard") }
        )
    }
}

@Composable
fun ActionButton(
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryGreen
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
