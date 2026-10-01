package com.treegrow.app.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trees")
data class Tree(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String,
    val name: String,
    val type: TreeType,
    val plantedDate: Long,
    val latitude: Double,
    val longitude: Double,
    val age: Int = 0,
    val health: Int = 100,
    val waterLevel: Int = 100,
    val imageUrl: String? = null,
    val isReal: Boolean = false,
    val realTreeId: String? = null,
    val ngoId: String? = null,
    val carbonCaptured: Double = 0.0
)

enum class TreeType {
    OAK,
    PINE,
    BIRCH,
    MAPLE,
    ASH,
    WILLOW,
    ELM,
    BEECH,
    SPRUCE,
    FIR
}
