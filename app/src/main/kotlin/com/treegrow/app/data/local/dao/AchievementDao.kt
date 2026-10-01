package com.treegrow.app.data.local.dao

import androidx.room.*
import com.treegrow.app.domain.models.Achievement
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: Achievement)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<Achievement>)

    @Query("SELECT * FROM achievements WHERE isLocked = 0 ORDER BY unlockedDate DESC")
    fun observeUnlockedAchievements(): Flow<List<Achievement>>

    @Query("SELECT * FROM achievements")
    suspend fun getAllAchievements(): List<Achievement>

    @Query("SELECT * FROM achievements WHERE isLocked = 0")
    suspend fun getUnlockedAchievements(): List<Achievement>

    @Update
    suspend fun updateAchievement(achievement: Achievement)

    @Query("DELETE FROM achievements")
    suspend fun clearAllAchievements()
}
