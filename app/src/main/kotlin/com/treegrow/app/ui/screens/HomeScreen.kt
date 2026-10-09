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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.treegrow.app.domain.models.Tree
import com.treegrow.app.domain.models.TreeType
import com.treegrow.app.presentation.viewmodel.AuthViewModel
import com.treegrow.app.presentation.viewmodel.HomeViewModel
import com.treegrow.app.ui.theme.PrimaryGreen
import com.treegrow.app.ui.theme.SecondaryGreen
import com.treegrow.app.ui.theme.TertiaryGreen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: AuthViewModel.LOCAL_DEVICE_USER_ID

    LaunchedEffect(userId) {
        viewModel.initializeUser(userId)
    }
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
            text = "Local tree records on this device",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        StatsSection(treeCount = uiState.userTrees.size)

        Spacer(modifier = Modifier.height(24.dp))

        TreeDisplaySection(trees = uiState.userTrees)

        if (uiState.error != null) {
            Text(
                text = uiState.error ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

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
            onClick = {
                val ownerId = uiState.userId.ifBlank { userId }
                viewModel.plantTree(
                    Tree(
                        userId = ownerId,
                        name = "Tree ${uiState.userTrees.size + 1}",
                        type = TreeType.OAK,
                        plantedDate = System.currentTimeMillis(),
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            },
            containerColor = PrimaryGreen,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Plant Tree")
        }
    }
}

@Composable
fun StatsSection(treeCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            title = "My Trees",
            value = treeCount.toString(),
            icon = "🌱",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            title = "Storage",
            value = "Device",
            icon = "📱",
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
fun TreeDisplaySection(trees: List<Tree>) {
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
                text = if (trees.isEmpty()) "No trees yet" else "${trees.size} saved",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (trees.isEmpty()) {
                    "Add a tree to keep a local record"
                } else {
                    trees.take(3).joinToString(" · ") { "${it.name} (${it.type.name.lowercase()})" }
                },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
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
