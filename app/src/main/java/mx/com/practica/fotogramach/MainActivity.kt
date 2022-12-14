package mx.com.practica.fotogramach

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import mx.com.practica.fotogramach.api.ApiServiceInterceptor
import mx.com.practica.fotogramach.databinding.ActivityMainBinding
import mx.com.practica.fotogramach.model.User

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = User.getLoggerInUser(this)
        if (user == null) {
            openLoginActivity()
            return
        } else {
            //Es para color el header de autentification
            ApiServiceInterceptor.setSessionToken(user._scid)
        }
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}