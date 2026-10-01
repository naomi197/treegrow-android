package com.treegrow.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treegrow.app.data.repository.TreeRepository
import com.treegrow.app.data.repository.UserRepository
import com.treegrow.app.domain.models.Tree
import com.treegrow.app.domain.usecase.GetUserStatsUseCase
import com.treegrow.app.domain.usecase.UserStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val userStats: UserStats? = null,
    val userTrees: List<Tree> = emptyList(),
    val nextTreeProgress: Float = 0f,
    val error: String? = null,
    val userId: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val treeRepository: TreeRepository,
    private val getUserStatsUseCase: GetUserStatsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun initializeUser(userId: String) {
        _uiState.update { it.copy(userId = userId) }
        loadUserData(userId)
    }

    private fun loadUserData(userId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Load user stats
                val statsResult = getUserStatsUseCase(userId)
                statsResult.onSuccess { stats ->
                    _uiState.update { it.copy(userStats = stats) }
                }
                statsResult.onFailure { error ->
                    Timber.e("Error loading stats: ${error.message}")
                    _uiState.update { it.copy(error = error.message) }
                }

                // Load user trees
                val treesResult = treeRepository.getUserTrees(userId)
                treesResult.onSuccess { trees ->
                    _uiState.update { it.copy(userTrees = trees) }
                }
                treesResult.onFailure { error ->
                    Timber.e("Error loading trees: ${error.message}")
                }

                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                Timber.e("Error: ${e.message}")
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun plantTree(tree: Tree) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = treeRepository.plantTree(tree)
                result.onSuccess { newTree ->
                    _uiState.update { state ->
                        state.copy(
                            userTrees = state.userTrees + newTree,
                            isLoading = false
                        )
                    }
                    Timber.d("Tree planted successfully")
                }
                result.onFailure { error ->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                    Timber.e("Error planting tree: ${error.message}")
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
                Timber.e("Exception: ${e.message}")
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
