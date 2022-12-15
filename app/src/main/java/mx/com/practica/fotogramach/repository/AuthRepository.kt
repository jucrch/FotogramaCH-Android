package mx.com.practica.fotogramach.repository

import mx.com.practica.fotogramach.api.ApiResponseStatus
import mx.com.practica.fotogramach.api.ApiService
import mx.com.practica.fotogramach.api.dto.Json
import mx.com.practica.fotogramach.api.dto.UserDTOMapper
import mx.com.practica.fotogramach.api.makeNetworkCall
import mx.com.practica.fotogramach.model.User
import mx.com.practica.fotogramach.security.AESEnDecryption
import mx.com.practica.fotogramach.utils.ivStr
import mx.com.practica.fotogramach.utils.keyStr
import org.json.JSONObject
import javax.inject.Inject


interface AuthTask {
    suspend fun login(
        user: String,
        password: String,
    ): ApiResponseStatus<User>
}

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
) : AuthTask {

    override suspend fun login(
        user: String,
        password: String,
    ): ApiResponseStatus<User> = makeNetworkCall {
        val obj = JSONObject()
        obj.put("usuario", user)
        obj.put("contrasena", password)
        obj.put("adressMAC", "")
        obj.put("version", 3)
        val security = AESEnDecryption()
        val ansBase64: String? = security.encryptStrAndToBase64(
            ivStr, keyStr,
            obj.toString()
        )
        val json = Json(ansBase64!!.replace("\n", ""))
        val loginApiResponse = apiService.login(json)
        if (loginApiResponse.isSuccess == 0) {
            throw java.lang.Exception(loginApiResponse.message)
        }
        val miObject = security.decryptStrAndFromBase64(ivStr, keyStr, loginApiResponse.miObject)!!
        val dtoMapper = UserDTOMapper()
        dtoMapper.fromUserDtoToUserDomain(miObject)
    }
}