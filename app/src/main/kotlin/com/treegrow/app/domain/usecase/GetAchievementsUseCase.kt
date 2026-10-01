package com.treegrow.app.domain.usecase

import com.treegrow.app.data.repository.AchievementRepository
import com.treegrow.app.domain.models.Achievement
import javax.inject.Inject

class GetAchievementsUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {
    suspend operator fun invoke(): Result<List<Achievement>> = runCatching {
        achievementRepository.fetchAchievements().getOrThrow()
    }

    suspend fun getUserAchievements(userId: String): Result<List<Achievement>> =
        achievementRepository.getUserAchievements(userId)
}
