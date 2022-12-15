package mx.com.practica.fotogramach.api.responses

import com.squareup.moshi.Json

class TiendasApiResponse(
    val message: String,
    @field:Json(name = "object")
    val miObject: String,
    @field:Json(name = "success")
    val isSuccess: Int,
    val version: Int,
)