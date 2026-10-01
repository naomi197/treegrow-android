package com.treegrow.app.data.repository

import com.treegrow.app.data.local.dao.TreeDao
import com.treegrow.app.data.remote.api.TreeGrowApiService
import com.treegrow.app.domain.models.Tree
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TreeRepository @Inject constructor(
    private val treeDao: TreeDao,
    private val apiService: TreeGrowApiService
) {

    suspend fun plantTree(tree: Tree): Result<Tree> = runCatching {
        val response = apiService.plantTree(tree)
        if (response.success && response.data != null) {
            treeDao.insertTree(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to plant tree")
        }
    }

    fun observeUserTrees(userId: String): Flow<List<Tree>> = treeDao.observeUserTrees(userId)

    suspend fun getUserTrees(userId: String): Result<List<Tree>> = runCatching {
        val response = apiService.getUserTrees(userId)
        if (response.success && response.data != null) {
            treeDao.insertTrees(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to fetch trees")
        }
    }

    suspend fun getTreeDetails(treeId: String): Result<Tree> = runCatching {
        val response = apiService.getTreeDetails(treeId)
        if (response.success && response.data != null) {
            treeDao.insertTree(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to fetch tree details")
        }
    }

    suspend fun updateTree(tree: Tree): Result<Tree> = runCatching {
        val response = apiService.updateTree(tree.id.toString(), tree)
        if (response.success && response.data != null) {
            treeDao.updateTree(response.data)
            response.data
        } else {
            throw Exception(response.message ?: "Failed to update tree")
        }
    }

    suspend fun getUserTreeCount(userId: String): Int = treeDao.getUserTreeCount(userId)

    suspend fun getUserTotalCarbonCaptured(userId: String): Double =
        treeDao.getUserTotalCarbonCaptured(userId) ?: 0.0
}
