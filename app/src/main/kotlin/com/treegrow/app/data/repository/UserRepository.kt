package com.treegrow.app.data.repository

import com.treegrow.app.data.local.dao.UserDao
import com.treegrow.app.data.remote.api.TreeGrowApiService
import com.treegrow.app.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val apiService: TreeGrowApiService
) {

    suspend fun createUser(user: User): Result<User> = runCatching {
        val response = apiService.createUser(user)
        if (response.success && response.data != null) {
            userDao.insertUser(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to create user")
        }
    }

    suspend fun getUserProfile(userId: String): Result<User> = runCatching {
        val response = apiService.getUserProfile(userId)
        if (response.success && response.data != null) {
            userDao.insertUser(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to fetch user profile")
        }
    }

    fun observeUserProfile(userId: String): Flow<User?> = userDao.observeUser(userId)

    suspend fun updateUserProfile(user: User): Result<User> = runCatching {
        val response = apiService.updateUserProfile(user.id, user)
        if (response.success && response.data != null) {
            userDao.updateUser(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to update profile")
        }
    }

    suspend fun getLocalUser(userId: String): User? = userDao.getUserById(userId)
}
