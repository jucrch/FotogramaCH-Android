package mx.com.practica.fotogramach.api

import mx.com.practica.fotogramach.api.dto.Json
import mx.com.practica.fotogramach.api.responses.AuthApiResponse
import mx.com.practica.fotogramach.api.responses.TiendasApiResponse
import mx.com.practica.fotogramach.utils.POST_CATTIENDAS
import mx.com.practica.fotogramach.utils.POST_LOGIN
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(POST_LOGIN)
    suspend fun login(@Body json: Json): AuthApiResponse

    @POST(POST_CATTIENDAS)
    suspend fun getTiendasAsignadas(@Body json: Json): TiendasApiResponse
}