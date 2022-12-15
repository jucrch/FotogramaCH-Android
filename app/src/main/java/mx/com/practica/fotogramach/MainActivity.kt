package mx.com.practica.fotogramach

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import mx.com.practica.fotogramach.theme.FotogramaCHTheme
import mx.com.practica.fotogramach.uicompose.MainScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val user = viewModel.user
//            val userValuer = user.value
//            if (userValuer != null) {
//                User.setLoggedInUser(this, userValuer)
//                startActivityMain()
//            }
//            val status = viewModel.status
            FotogramaCHTheme {
                MainScreen(
//                    onTiendaSelect = { openLoginActivity() }
                )
            }
        }
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}