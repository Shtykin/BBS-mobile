package ru.shtykin.bbs_mobile.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetDoorsUseCase
import ru.shtykin.bbs_mobile.presentation.state.ScreenState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCamerasUseCase: GetCamerasUseCase,
    private val getDoorsUseCase: GetDoorsUseCase
) : ViewModel() {

    private val _uiState =
        mutableStateOf<ScreenState>(
            ScreenState.CamerasScreen(
                temp = "temp"
            )
        )

    val uiState: State<ScreenState>
        get() = _uiState

    fun getCameras() {
        viewModelScope.launch(Dispatchers.IO) {
            getCamerasUseCase.execute()
        }
    }

    fun getDoors() {
        viewModelScope.launch(Dispatchers.IO) {
            getCamerasUseCase.execute()
        }
    }


}