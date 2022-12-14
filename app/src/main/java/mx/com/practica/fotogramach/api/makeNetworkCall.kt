package mx.com.practica.fotogramach.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.practica.fotogramach.R
import retrofit2.HttpException
import java.net.UnknownHostException

private const val UNAUTHORIZED_ERROR_CODE = 401

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (en: UnknownHostException) {
        ApiResponseStatus.Error(R.string.not_internet)
    } catch (e: HttpException) {
        val errorMessage = if (e.code() == UNAUTHORIZED_ERROR_CODE) {
            R.string.wrong_user_or_password
        } else {
            R.string.unkown_error
        }
        ApiResponseStatus.Error(errorMessage)
    } catch (e: Exception) {
        val errorMessage = when (e.message) {
            "sign_up_error" -> R.string.error_sign_up
            "sign_in_error" -> R.string.error_sign_in
            "sign_already_exists" -> R.string.user_already_exists
            "error_adding_dog" -> R.string.error_adding_dog
            else -> R.string.unkown_error
        }
        ApiResponseStatus.Error(errorMessage)
    }
}