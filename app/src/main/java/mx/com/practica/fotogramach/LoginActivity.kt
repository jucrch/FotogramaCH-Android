package mx.com.practica.fotogramach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import mx.com.practica.fotogramach.theme.FotogramaCHTheme
import mx.com.practica.fotogramach.uicompose.LoginScreen
import mx.com.practica.fotogramach.viewmodels.AuthViewModel

class LoginActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FotogramaCHTheme {
                LoginScreen(
                    status = viewModel.status.value,
                    authViewModel = viewModel,
                    onLoginButtonClick = { email, password -> viewModel.login(email, password) })
            }
        }
    }
}