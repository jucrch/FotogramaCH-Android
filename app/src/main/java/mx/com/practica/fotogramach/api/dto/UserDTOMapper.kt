package mx.com.practica.fotogramach.api.dto

import mx.com.practica.fotogramach.model.User
import org.json.JSONObject

class UserDTOMapper {
    //    fun fromUserDtoToUserDomain(userDto: UserDto) =
//        User(userDto.id, userDto.email, "")
//
    fun fromUserDtoToUserDomain(miObject: String?): User {
        val miUser = JSONObject(miObject!!)
        return User(
            miUser.getString("_scid"),
            miUser.getString("nombre"),
            miUser.getString("apPaterno"),
            miUser.getString("apMaterno"),
        )
    }
}