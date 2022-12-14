package mx.com.practica.fotogramach.model

import android.app.Activity
import android.content.Context

class User(
    val _scid: String,
    val nombre: String,
    val apPaterno: String,
    val apMaterno: String,
) {
    companion object {

        private const val AUTH_PREFS = "auth_prefs"
        private const val ID_KEY = "scid"
        private const val USER_KEY = "usuario"
        private const val AP_PATERNO_KEY = "apPaterno"
        private const val AP_MATERNO_KEY = "apMaterno"

        fun setLoggedInUser(activity: Activity, user: User) {
            activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).also {
                it.edit()
                    .putString(ID_KEY, user._scid)
                    .putString(USER_KEY, user.nombre)
                    .putString(AP_PATERNO_KEY, user.apPaterno)
                    .putString(AP_MATERNO_KEY, user.apMaterno).apply()
            }
        }

        fun getLoggerInUser(activity: Activity): User? {
            val prefs =
                activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE) ?: return null
            val userId = prefs.getString(ID_KEY, "")
            if (userId!!.isEmpty()) {
                return null
            }
            return User(
                prefs.getString(ID_KEY, "") ?: "",
                prefs.getString(USER_KEY, "") ?: "",
                prefs.getString(AP_PATERNO_KEY, "") ?: "",
                prefs.getString(AP_MATERNO_KEY, "") ?: ""
            )
        }

        fun logout(activity: Activity) {
            activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE)
                .also { it.edit().clear().apply() }
        }
    }
}