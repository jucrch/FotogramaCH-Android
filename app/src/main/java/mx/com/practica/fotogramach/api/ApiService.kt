package mx.com.practica.fotogramach.api

import mx.com.practica.fotogramach.api.dto.Json
import mx.com.practica.fotogramach.api.responses.AuthApiResponse
import mx.com.practica.fotogramach.utils.BASE_URL
import mx.com.practica.fotogramach.utils.POST_LOGIN
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val okHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(ApiServiceInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService {
    @POST(POST_LOGIN)
    suspend fun login(@Body json: Json): AuthApiResponse
}

object FotogramaApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}