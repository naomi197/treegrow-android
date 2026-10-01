package com.treegrow.app.data.remote.api

import com.treegrow.app.domain.models.Tree
import com.treegrow.app.domain.models.User
import com.treegrow.app.domain.models.Achievement
import retrofit2.http.*

interface TreeGrowApiService {

    // User endpoints
    @GET("users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): ApiResponse<User>

    @POST("users")
    suspend fun createUser(@Body user: User): ApiResponse<User>

    @PUT("users/{userId}")
    suspend fun updateUserProfile(
        @Path("userId") userId: String,
        @Body user: User
    ): ApiResponse<User>

    // Tree endpoints
    @GET("trees")
    suspend fun getUserTrees(
        @Query("userId") userId: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<List<Tree>>

    @POST("trees")
    suspend fun plantTree(@Body tree: Tree): ApiResponse<Tree>

    @GET("trees/{treeId}")
    suspend fun getTreeDetails(@Path("treeId") treeId: String): ApiResponse<Tree>

    @PUT("trees/{treeId}")
    suspend fun updateTree(
        @Path("treeId") treeId: String,
        @Body tree: Tree
    ): ApiResponse<Tree>

    // Achievement endpoints
    @GET("achievements")
    suspend fun getAchievements(): ApiResponse<List<Achievement>>

    @GET("users/{userId}/achievements")
    suspend fun getUserAchievements(@Path("userId") userId: String): ApiResponse<List<Achievement>>

    // Leaderboard endpoints
    @GET("leaderboard")
    suspend fun getLeaderboard(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): ApiResponse<List<LeaderboardEntry>>

    @GET("leaderboard/{userId}")
    suspend fun getUserRank(@Path("userId") userId: String): ApiResponse<LeaderboardEntry>
}

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class LeaderboardEntry(
    val rank: Int,
    val userId: String,
    val username: String,
    val points: Int,
    val totalTrees: Int,
    val avatar: String?
)
