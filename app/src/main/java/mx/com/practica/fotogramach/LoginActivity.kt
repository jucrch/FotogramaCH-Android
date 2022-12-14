package mx.com.practica.fotogramach

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import mx.com.practica.fotogramach.model.User
import mx.com.practica.fotogramach.theme.FotogramaCHTheme
import mx.com.practica.fotogramach.uicompose.LoginScreen
import mx.com.practica.fotogramach.viewmodels.AuthViewModel

class LoginActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val user = viewModel.user
            val userValuer = user.value
            if (userValuer != null) {
                User.setLoggedInUser(this, userValuer)
                startActivityMain()
            }
            val status = viewModel.status
            FotogramaCHTheme {
                LoginScreen(
                    status = viewModel.status.value,
                    authViewModel = viewModel,
                    onLoginButtonClick = { user, password -> viewModel.login(
                        user = user,
                        password = password
                    ) },
                    onErrorDialogDismiss = ::resetApiResponseStatus
                )
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun startActivityMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun resetApiResponseStatus() {
        viewModel.resetApiResponseStatus()
    }
}