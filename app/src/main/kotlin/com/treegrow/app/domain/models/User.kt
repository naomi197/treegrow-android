package com.treegrow.app.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val username: String,
    val email: String,
    val avatar: String? = null,
    val level: Int = 1,
    val points: Int = 0,
    val totalTrees: Int = 0,
    val totalRealTrees: Int = 0,
    val carbonCaptured: Double = 0.0,
    val joinedDate: Long,
    val bio: String? = null,
    val country: String? = null,
    val achievements: List<String> = emptyList(),
    val rank: Int = 0
)
