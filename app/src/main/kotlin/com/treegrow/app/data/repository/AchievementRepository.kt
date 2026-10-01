package com.treegrow.app.data.repository

import com.treegrow.app.data.local.dao.AchievementDao
import com.treegrow.app.data.remote.api.TreeGrowApiService
import com.treegrow.app.domain.models.Achievement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AchievementRepository @Inject constructor(
    private val achievementDao: AchievementDao,
    private val apiService: TreeGrowApiService
) {

    suspend fun fetchAchievements(): Result<List<Achievement>> = runCatching {
        val response = apiService.getAchievements()
        if (response.success && response.data != null) {
            achievementDao.insertAchievements(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to fetch achievements")
        }
    }

    fun observeUnlockedAchievements(): Flow<List<Achievement>> =
        achievementDao.observeUnlockedAchievements()

    suspend fun getUserAchievements(userId: String): Result<List<Achievement>> = runCatching {
        val response = apiService.getUserAchievements(userId)
        if (response.success && response.data != null) {
            response.data
        } else {
            throw Exception(response.message ?: "Failed to fetch user achievements")
        }
    }

    suspend fun getUnlockedAchievements(): List<Achievement> =
        achievementDao.getUnlockedAchievements()
}
