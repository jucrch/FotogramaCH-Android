package mx.com.practica.fotogramach

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import mx.com.practica.fotogramach.model.User
import mx.com.practica.fotogramach.theme.FotogramaCHTheme
import mx.com.practica.fotogramach.uicompose.MainScreen
import mx.com.practica.fotogramach.viewmodels.TiendasViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tiendasViewModel: TiendasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = User.getLoggerInUser(this)
        if (user == null) {
            openLoginActivity()
            return
        }

        tiendasViewModel.getTiendasAsigColletion(user._scid)
        setContent {
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