package mx.com.practica.fotogramach.repository

import mx.com.practica.fotogramach.api.ApiResponseStatus
import mx.com.practica.fotogramach.api.ApiService
import mx.com.practica.fotogramach.api.dto.Json
import mx.com.practica.fotogramach.api.dto.TiendasAsigDtoMapper
import mx.com.practica.fotogramach.api.makeNetworkCall
import mx.com.practica.fotogramach.model.TiendasAsignadas
import mx.com.practica.fotogramach.security.AESEnDecryption
import mx.com.practica.fotogramach.utils.ivStr
import mx.com.practica.fotogramach.utils.keyStr
import org.json.JSONObject
import javax.inject.Inject


interface TiendasTask {
    suspend fun getTiendasAsignadas(scid: String): ApiResponseStatus<List<TiendasAsignadas>>
}

class TiendasRepository @Inject constructor(
    private val apiService: ApiService,
) : TiendasTask {
    override suspend fun getTiendasAsignadas(scid: String):
            ApiResponseStatus<List<TiendasAsignadas>> =
        makeNetworkCall {
            val obj = JSONObject()
            obj.put("_scid", scid)
            obj.put("adressMAC", "")
            obj.put("version", 3)
            val security = AESEnDecryption()
            val ansBase64: String? = security.encryptStrAndToBase64(
                ivStr, keyStr,
                obj.toString()
            )
            val json = Json(ansBase64!!.replace("\n", ""))
            val tienndasAsigListApiResponse = apiService.getTiendasAsignadas(json)
//            val lsDogDto = dogListApiResponse.data.dogs
            if (tienndasAsigListApiResponse.isSuccess == 0) {
                throw Exception(tienndasAsigListApiResponse.message)
            }
            val dtoMapper = TiendasAsigDtoMapper()
            val miObject = security.decryptStrAndFromBase64(
                ivStr,
                keyStr,
                tienndasAsigListApiResponse.miObject
            )!!
            dtoMapper.fromListTiendasAsignadasDomain(miObject)
        }
}