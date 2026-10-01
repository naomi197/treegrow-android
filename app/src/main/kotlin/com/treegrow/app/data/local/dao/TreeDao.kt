package com.treegrow.app.data.local.dao

import androidx.room.*
import com.treegrow.app.domain.models.Tree
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTree(tree: Tree)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrees(trees: List<Tree>)

    @Query("SELECT * FROM trees WHERE userId = :userId ORDER BY plantedDate DESC")
    fun observeUserTrees(userId: String): Flow<List<Tree>>

    @Query("SELECT * FROM trees WHERE userId = :userId ORDER BY plantedDate DESC")
    suspend fun getUserTrees(userId: String): List<Tree>

    @Query("SELECT * FROM trees WHERE id = :treeId")
    suspend fun getTreeById(treeId: Int): Tree?

    @Query("SELECT COUNT(*) FROM trees WHERE userId = :userId")
    suspend fun getUserTreeCount(userId: String): Int

    @Query("SELECT SUM(carbonCaptured) FROM trees WHERE userId = :userId")
    suspend fun getUserTotalCarbonCaptured(userId: String): Double?

    @Update
    suspend fun updateTree(tree: Tree)

    @Delete
    suspend fun deleteTree(tree: Tree)

    @Query("DELETE FROM trees WHERE userId = :userId")
    suspend fun deleteUserTrees(userId: String)
}
