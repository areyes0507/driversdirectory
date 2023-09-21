package com.rodriguez.driversdirectory.ui.drivers

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodriguez.driversdirectory.R
import com.rodriguez.driversdirectory.data.ObjResult
import com.rodriguez.driversdirectory.domain.model.Driver
import com.rodriguez.driversdirectory.domain.usecases.DownloadDriversAndRoutesUseCase
import com.rodriguez.driversdirectory.domain.usecases.DriversUseCase
import com.rodriguez.driversdirectory.domain.usecases.SortDriversUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriversViewModel @Inject constructor(
    private val downloadDriversAndRoutesUseCase: DownloadDriversAndRoutesUseCase,
    private val driversUseCase: DriversUseCase,
    private val sortDriversUseCase: SortDriversUseCase,
    private @ApplicationContext val appContext: Context
) : ViewModel() {

    private val _driversState = MutableStateFlow(DriversUiState())
    val driversUiState: StateFlow<DriversUiState> = _driversState

    init {
        loadDriversAndRoutes()
    }

    private fun loadDriversAndRoutes() {
        viewModelScope.launch {
            downloadDriversAndRoutesUseCase().onEach {
                when (it) {
                    ObjResult.ConnectionError -> _driversState.value = _driversState.value.copy(
                        error = appContext.getString(R.string.connection_error)
                    )

                    is ObjResult.Error -> _driversState.value = _driversState.value.copy(
                        error = it.exception.message.takeIf { message -> message != null }
                            ?: appContext.getString(R.string.server_error),
                        loading = false
                    )

                    ObjResult.Loading -> _driversState.value = _driversState.value.copy(
                        loading = true
                    )

                    is ObjResult.Success -> _driversState.value = _driversState.value.copy(
                        drivers = driversUseCase(),
                        loading = false,
                        error = null
                    )
                }
            }.collect()
        }
    }

    fun sortCurrentDriversList() {
        viewModelScope.launch {
            _driversState.value = _driversState.value.copy(
                drivers = sortDriversUseCase()
            )
        }
    }

    fun onDismissDialog() {
        _driversState.value = _driversState.value.copy(
            error = null
        )
    }

    data class DriversUiState(
        val drivers: List<Driver> = emptyList(),
        val error: String? = null,
        val loading: Boolean = false
    )
}