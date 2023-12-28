package ru.shtykin.bbs_mobile.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasFromDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetDoorsUseCase
import ru.shtykin.bbs_mobile.domain.usecase.UpdateAllCamerasToDbUseCase
import ru.shtykin.bbs_mobile.presentation.state.ScreenState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCamerasUseCase: GetCamerasUseCase,
    private val getDoorsUseCase: GetDoorsUseCase,
    private val updateAllCamerasToDbUseCase: UpdateAllCamerasToDbUseCase,
    private val getCamerasFromDbUseCase: GetCamerasFromDbUseCase
) : ViewModel() {

    private val _uiState =
        mutableStateOf<ScreenState>(
            ScreenState.CamerasScreen(
                cameras = emptyList(),
                refreshing = false
            )
        )

    val uiState: State<ScreenState>
        get() = _uiState

    fun getCameras() {
        _uiState.value = ScreenState.CamerasScreen(
            cameras = emptyList(),
            refreshing = true
        )
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val cameras = getCamerasUseCase.execute()
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.CamerasScreen(
                        cameras = cameras,
                        refreshing = false
                    )
                }

                updateAllCamerasToDbUseCase.execute(cameras)
                val result = getCamerasFromDbUseCase.execute()
                Log.e("DEBUG1", "result -> ${result}")
            } catch (e: Exception) {
                Log.e("DEBUG1", "exception -> ${e.message}")
            }
        }
    }

    fun getDoors() {
        _uiState.value = ScreenState.DoorsScreen(
            doors = emptyList(),
            refreshing = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val doors = getDoorsUseCase.execute()
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.DoorsScreen(
                        doors = doors,
                        refreshing = false
                    )
                }
            } catch (e: Exception) {
                Log.e("DEBUG1", "exception -> ${e.message}")
            }
        }
    }


}