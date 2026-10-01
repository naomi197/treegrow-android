package com.treegrow.app.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class Achievement(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val type: AchievementType,
    val points: Int,
    val requirement: Int,
    val unlockedDate: Long? = null,
    val isLocked: Boolean = true
)

enum class AchievementType {
    TREE_PLANTER,
    NATURE_LOVER,
    COMMUNITY_HELPER,
    CHALLENGE_MASTER,
    ENVIRONMENTAL_HERO,
    SOCIAL_BUTTERFLY,
    WORLD_EXPLORER,
    MILESTONE
}
