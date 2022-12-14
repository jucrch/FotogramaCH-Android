package mx.com.practica.fotogramach.api.dto

class LoginDto(
    val usuario: String,
    val contrasena: String,
    val adressMAC: String,
    val version: Int,
) {
    override fun toString(): String {
        return "{\"usuario\":\"$usuario\",\"contrasena\":\"$contrasena\",\"adressMAC\":\"$adressMAC\",\"version\":$version}"
    }
}