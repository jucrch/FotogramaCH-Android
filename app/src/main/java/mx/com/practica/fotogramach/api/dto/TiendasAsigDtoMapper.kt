package mx.com.practica.fotogramach.api.dto

import mx.com.practica.fotogramach.model.TiendasAsignadas
import org.json.JSONArray

class TiendasAsigDtoMapper {

    fun fromListTiendasAsignadasDomain(miObject: String?): List<TiendasAsignadas> {
        val miUser = JSONArray(miObject!!)
        var lsTiendasAsignadas = ArrayList<TiendasAsignadas>()
        for (i in 0 until miUser.length()) {
            val item = miUser.getJSONObject(i)
            lsTiendasAsignadas.add(
                TiendasAsignadas(
                    item.getInt("idTienda"),
                    item.getString("nomTienda")
                )
            )
        }
        return lsTiendasAsignadas
    }
}