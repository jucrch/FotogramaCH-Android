package mx.com.practica.fotogramach.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.com.practica.fotogramach.R
import mx.com.practica.fotogramach.api.ApiResponseStatus
import mx.com.practica.fotogramach.model.User
import mx.com.practica.fotogramach.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var user = mutableStateOf<User?>(null)
        private set

    var status = mutableStateOf<ApiResponseStatus<User>?>(null)
        private set

    var userError = mutableStateOf<Int?>(null)
        private set
    var passwordError = mutableStateOf<Int?>(null)
        private set

    init {

    }

    fun login(user: String, password: String) {
        when {
            user.isEmpty() -> {
                userError.value = R.string.user_is_not_valid
            }
            password.isEmpty() -> {
                passwordError.value = R.string.password_is_not_valid
            }
            else -> {
                status.value = ApiResponseStatus.Loading()
                viewModelScope.launch {
                    handleResponseStatus(authRepository.login(user, password))
                }
            }
        }
    }

    fun resetErrors() {
        userError.value = null
        passwordError.value = null
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<User>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            user.value = apiResponseStatus.data!!
        }
        status.value = apiResponseStatus
    }

    fun resetApiResponseStatus() {
        status.value = null
    }
}