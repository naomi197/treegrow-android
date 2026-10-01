package com.treegrow.app.domain.models

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val targetTrees: Int,
    val reward: Int,
    val startDate: Long,
    val endDate: Long,
    val participants: Int = 0,
    val isFeatured: Boolean = false,
    val category: String,
    val difficulty: Difficulty = Difficulty.MEDIUM
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD,
    LEGENDARY
}
