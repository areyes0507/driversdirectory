package com.rodriguez.driversdirectory.ui.routes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodriguez.driversdirectory.domain.model.Route
import com.rodriguez.driversdirectory.domain.usecases.RoutesUseCase
import com.rodriguez.driversdirectory.ui.navigation.RoutesDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val routesUseCase: RoutesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _routesState = MutableStateFlow(emptyList<Route>())
    val routesUiState: StateFlow<List<Route>> = _routesState
    private val driverIdentifierSelected = savedStateHandle[RoutesDestination.driverIdentifierArgument]
        ?: -1

    init {
        loadRoutes()
    }

    private fun loadRoutes() {
        viewModelScope.launch {
            _routesState.value = routesUseCase(driverIdentifierSelected)
        }
    }
}