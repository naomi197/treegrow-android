package com.treegrow.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.treegrow.app.ui.theme.PrimaryGreen
import com.treegrow.app.ui.theme.SecondaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(navController: NavController) {
    val leaderboardData = listOf(
        LeaderboardEntry(1, "علیرضا", 5420, "🥇"),
        LeaderboardEntry(2, "فاطمه", 4830, "🥈"),
        LeaderboardEntry(3, "محمد", 4210, "🥉"),
        LeaderboardEntry(4, "زهرا", 3890, ""),
        LeaderboardEntry(5, "حسن", 3450, "")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "📊 رتبه‌بندی کاربران",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryGreen
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            items(leaderboardData) { entry ->
                LeaderboardItem(entry)
            }
        }
    }
}

@Composable
fun LeaderboardItem(entry: LeaderboardEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (entry.rank) {
                1 -> Color(0xFFFFD700).copy(alpha = 0.1f)
                2 -> Color(0xFFC0C0C0).copy(alpha = 0.1f)
                3 -> Color(0xFFCD7F32).copy(alpha = 0.1f)
                else -> Color.White
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(SecondaryGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (entry.medal.isNotEmpty()) {
                    Text(entry.medal, style = MaterialTheme.typography.headlineSmall)
                } else {
                    Text(
                        "#${entry.rank}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    entry.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "${entry.points} نقطه",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Text(
                "🌳 ${entry.rank * 2}",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

data class LeaderboardEntry(
    val rank: Int,
    val name: String,
    val points: Int,
    val medal: String
)
