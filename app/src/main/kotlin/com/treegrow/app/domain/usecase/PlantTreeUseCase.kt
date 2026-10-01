package com.treegrow.app.domain.usecase

import com.treegrow.app.data.repository.TreeRepository
import com.treegrow.app.domain.models.Tree
import javax.inject.Inject

class PlantTreeUseCase @Inject constructor(
    private val treeRepository: TreeRepository
) {
    suspend operator fun invoke(tree: Tree): Result<Tree> {
        return try {
            if (tree.name.isBlank()) {
                Result.failure(Exception("Tree name cannot be empty"))
            } else if (tree.latitude < -90 || tree.latitude > 90) {
                Result.failure(Exception("Invalid latitude"))
            } else if (tree.longitude < -180 || tree.longitude > 180) {
                Result.failure(Exception("Invalid longitude"))
            } else {
                treeRepository.plantTree(tree)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
