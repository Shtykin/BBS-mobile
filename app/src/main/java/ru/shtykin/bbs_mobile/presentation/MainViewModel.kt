package ru.shtykin.bbs_mobile.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasFromDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetDoorsFromDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetDoorsUseCase
import ru.shtykin.bbs_mobile.domain.usecase.ToggleFavoriteCameraInDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.UpdateAllCamerasToDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.UpdateAllDoorsToDbUseCase
import ru.shtykin.bbs_mobile.presentation.state.ScreenState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCamerasUseCase: GetCamerasUseCase,
    private val getDoorsUseCase: GetDoorsUseCase,
    private val updateAllCamerasToDbUseCase: UpdateAllCamerasToDbUseCase,
    private val getCamerasFromDbUseCase: GetCamerasFromDbUseCase,
    private val toggleFavoriteCameraInDbUseCase: ToggleFavoriteCameraInDbUseCase,
    private val getDoorsFromDbUseCase: GetDoorsFromDbUseCase,
    private val updateAllDoorsToDbUseCase: UpdateAllDoorsToDbUseCase
) : ViewModel() {

    private val _uiState =
        mutableStateOf<ScreenState>(
            ScreenState.CamerasScreen(
                cameras = emptyList(),
                refreshing = false,
                error = null
            )
        )

    val uiState: State<ScreenState>
        get() = _uiState

    init {
        getCameras()
    }

    fun getCamerasIntend() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = ScreenState.CamerasScreen(
                    cameras = getCamerasFromDb(),
                    refreshing = true,
                    error = null
                )
                val camerasFromNetwork = getCamerasUseCase.execute()
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.CamerasScreen(
                        cameras = camerasFromNetwork,
                        refreshing = false,
                        error = null
                    )
                }
                updateAllCamerasToDbUseCase.execute(camerasFromNetwork)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.CamerasScreen(
                        cameras = getCamerasFromDb(),
                        refreshing = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun getCameras() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val camerasFromDb = getCamerasFromDb()
                if (camerasFromDb.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _uiState.value = ScreenState.CamerasScreen(
                            cameras = camerasFromDb,
                            refreshing = false,
                            error = null
                        )
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _uiState.value = ScreenState.CamerasScreen(
                            cameras = camerasFromDb,
                            refreshing = true,
                            error = null
                        )
                    }
                    val camerasFromNetwork = getCamerasUseCase.execute()
                    withContext(Dispatchers.Main) {
                        _uiState.value = ScreenState.CamerasScreen(
                            cameras = camerasFromNetwork,
                            refreshing = false,
                            error = null
                        )
                    }
                    updateAllCamerasToDbUseCase.execute(camerasFromNetwork)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.CamerasScreen(
                        cameras = getCamerasFromDb(),
                        refreshing = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun toggleFavoriteCamera(camera: Camera) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                toggleFavoriteCameraInDbUseCase.execute(camera)
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.CamerasScreen(
                        cameras = getCamerasFromDb(),
                        refreshing = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.CamerasScreen(
                        cameras = emptyList(),
                        refreshing = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun getDoorsIntend() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = ScreenState.DoorsScreen(
                    doors = getDoorsFromDb(),
                    refreshing = true,
                    error = null
                )
                val doorsFromNetwork = getDoorsUseCase.execute()
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.DoorsScreen(
                        doors = doorsFromNetwork,
                        refreshing = false,
                        error = null
                    )
                }
                updateAllDoorsToDbUseCase.execute(doorsFromNetwork)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.DoorsScreen(
                        doors = getDoorsFromDb(),
                        refreshing = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun getDoors() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val doorsFromDb = getDoorsFromDb()
                if (doorsFromDb.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _uiState.value = ScreenState.DoorsScreen(
                            doors = doorsFromDb,
                            refreshing = false,
                            error = null
                        )
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _uiState.value = ScreenState.DoorsScreen(
                            doors = doorsFromDb,
                            refreshing = true,
                            error = null
                        )
                    }
                    val doorsFromNetwork = getDoorsUseCase.execute()
                    withContext(Dispatchers.Main) {
                        _uiState.value = ScreenState.DoorsScreen(
                            doors = doorsFromNetwork,
                            refreshing = false,
                            error = null
                        )
                    }
                    updateAllDoorsToDbUseCase.execute(doorsFromNetwork)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = ScreenState.DoorsScreen(
                        doors = getDoorsFromDb(),
                        refreshing = false,
                        error = e.message
                    )
                }
            }
        }
    }

    private suspend fun getCamerasFromDb() = getCamerasFromDbUseCase.execute()
    private suspend fun getDoorsFromDb() = getDoorsFromDbUseCase.execute()

}