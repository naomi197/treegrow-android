package com.treegrow.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.treegrow.app.data.repository.UserRepository
import com.treegrow.app.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

data class LoginUiState(
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val error: String? = null,
    val userId: String? = null
)

data class SignupUiState(
    val isLoading: Boolean = false,
    val signupSuccess: Boolean = false,
    val error: String? = null,
    val userId: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    private val _signupUiState = MutableStateFlow(SignupUiState())
    val signupUiState = _signupUiState.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginUiState.update { it.copy(error = "البريد الإلكتروني وكلمة المرور مطلوبة") }
            return
        }

        viewModelScope.launch {
            _loginUiState.update { it.copy(isLoading = true) }
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                val userId = result.user?.uid ?: throw Exception("User ID not found")

                _loginUiState.update {
                    it.copy(
                        isLoading = false,
                        loginSuccess = true,
                        userId = userId
                    )
                }
                Timber.d("Login successful: $userId")
            } catch (e: Exception) {
                _loginUiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "فشل في الدخول"
                    )
                }
                Timber.e("Login failed: ${e.message}")
            }
        }
    }

    fun signup(username: String, email: String, password: String, confirmPassword: String) {
        // Validation
        when {
            username.isBlank() -> {
                _signupUiState.update { it.copy(error = "اسم المستخدم مطلوب") }
                return
            }
            email.isBlank() -> {
                _signupUiState.update { it.copy(error = "البريد الإلكتروني مطلوب") }
                return
            }
            password.isBlank() -> {
                _signupUiState.update { it.copy(error = "كلمة المرور مطلوبة") }
                return
            }
            password.length < 6 -> {
                _signupUiState.update { it.copy(error = "يجب أن تكون كلمة المرور 6 أحرف على الأقل") }
                return
            }
            password != confirmPassword -> {
                _signupUiState.update { it.copy(error = "كلمات المرور غير متطابقة") }
                return
            }
        }

        viewModelScope.launch {
            _signupUiState.update { it.copy(isLoading = true) }
            try {
                // Create Firebase Auth user
                val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val userId = authResult.user?.uid ?: throw Exception("User ID not found")

                // Create user profile
                val newUser = User(
                    id = userId,
                    username = username,
                    email = email,
                    joinedDate = System.currentTimeMillis()
                )

                userRepository.createUser(newUser).onSuccess {
                    _signupUiState.update {
                        it.copy(
                            isLoading = false,
                            signupSuccess = true,
                            userId = userId
                        )
                    }
                    Timber.d("Signup successful: $userId")
                }.onFailure { error ->
                    _signupUiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "فشل في إنشاء الحساب"
                        )
                    }
                    Timber.e("Signup failed: ${error.message}")
                }
            } catch (e: Exception) {
                _signupUiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "فشل في التسجيل"
                    )
                }
                Timber.e("Signup exception: ${e.message}")
            }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        _loginUiState.update { LoginUiState() }
        _signupUiState.update { SignupUiState() }
    }
}
