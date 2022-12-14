package mx.com.practica.fotogramach.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.com.practica.fotogramach.R
import mx.com.practica.fotogramach.api.ApiResponseStatus
import mx.com.practica.fotogramach.model.User

class AuthViewModel : ViewModel() {

    var user = mutableStateOf<User?>(null)
        private set

    var status = mutableStateOf<ApiResponseStatus<User>?>(null)
        private set

    var userError = mutableStateOf<Int?>(null)
        private set
    var passwordError = mutableStateOf<Int?>(null)
        private set

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
                
            }
        }
    }

    fun resetErrors() {
        userError.value = null
        passwordError.value = null
    }
}