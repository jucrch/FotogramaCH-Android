package mx.com.practica.fotogramach.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.practica.fotogramach.api.ApiResponseStatus
import mx.com.practica.fotogramach.model.TiendasAsignadas
import mx.com.practica.fotogramach.repository.TiendasTask
import javax.inject.Inject


@HiltViewModel
class TiendasViewModel @Inject constructor(
    private val tiendasRepository: TiendasTask,
) : ViewModel() {

    var tiendasList = mutableStateOf<List<TiendasAsignadas>>(listOf())
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    fun getTiendasAsigColletion(scid: String) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(
                tiendasRepository.getTiendasAsignadas(scid)
            )
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<TiendasAsignadas>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            tiendasList.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus() {
        status.value = null
    }
}