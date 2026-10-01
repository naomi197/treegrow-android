package com.treegrow.app.domain.usecase

import com.treegrow.app.data.repository.TreeRepository
import com.treegrow.app.data.repository.UserRepository
import javax.inject.Inject

data class UserStats(
    val totalTrees: Int = 0,
    val totalCarbonCaptured: Double = 0.0,
    val level: Int = 1,
    val points: Int = 0
)

class GetUserStatsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val treeRepository: TreeRepository
) {
    suspend operator fun invoke(userId: String): Result<UserStats> = runCatching {
        val user = userRepository.getLocalUser(userId)
            ?: throw Exception("User not found")
        
        val totalTrees = treeRepository.getUserTreeCount(userId)
        val totalCarbon = treeRepository.getUserTotalCarbonCaptured(userId)

        UserStats(
            totalTrees = totalTrees,
            totalCarbonCaptured = totalCarbon,
            level = user.level,
            points = user.points
        )
    }
}
