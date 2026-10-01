package com.treegrow.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treegrow.app.data.repository.AchievementRepository
import com.treegrow.app.domain.models.Achievement
import com.treegrow.app.domain.usecase.GetAchievementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class AchievementsUiState(
    val isLoading: Boolean = false,
    val achievements: List<Achievement> = emptyList(),
    val unlockedCount: Int = 0,
    val error: String? = null
)

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    private val getAchievementsUseCase: GetAchievementsUseCase,
    private val achievementRepository: AchievementRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AchievementsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadAchievements()
    }

    private fun loadAchievements() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = getAchievementsUseCase()
                result.onSuccess { achievements ->
                    val unlockedCount = achievements.count { !it.isLocked }
                    _uiState.update { state ->
                        state.copy(
                            achievements = achievements,
                            unlockedCount = unlockedCount,
                            isLoading = false
                        )
                    }
                    Timber.d("Achievements loaded: ${achievements.size}")
                }
                result.onFailure { error ->
                    _uiState.update { 
                        it.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                    Timber.e("Error loading achievements: ${error.message}")
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
                Timber.e("Exception: ${e.message}")
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
