package com.rodriguez.driversdirectory.ui.drivers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodriguez.driversdirectory.R
import com.rodriguez.driversdirectory.domain.model.Driver
import com.rodriguez.driversdirectory.ui.DriversDirectoryTopBar
import com.rodriguez.driversdirectory.ui.theme.DriversDirectoryTheme

@Composable
fun DriversScreen(driversViewModel: DriversViewModel, onDriverClick: (Driver) -> Unit) {
    val driversUiState = driversViewModel.driversUiState.collectAsState().value
    Surface {
        DriversScreenContent(driversUiState = driversUiState, onDriverClick = onDriverClick,
            onDismissDialog = { driversViewModel.onDismissDialog() },
            onSortClicked = { driversViewModel.sortCurrentDriversList() })
    }
}

@Composable
fun DriversScreenContent(driversUiState: DriversViewModel.DriversUiState,
                         onDismissDialog: () -> Unit,
                         onSortClicked: () -> Unit,
                         onDriverClick: (Driver) -> Unit) {
    driversUiState.error?.let {
        AlertDialog(
            title = { Text(text = stringResource(R.string.drivers_information_dialog_title)) },
            text = { Text(text = it) },
            onDismissRequest = onDismissDialog, confirmButton = {
                Button(onClick = onDismissDialog) {
                    Text(stringResource(R.string.drivers_information_dialog_close))
                }
            })
    }

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize())
    {
        val drivers = driversUiState.drivers
        item {
            DriversDirectoryTopBar(onSortClicked = onSortClicked, showAction = true)
        }
        items(items = drivers, key = { driver -> driver.id }) {
            DriverItem(driver = it, onDriverClick = onDriverClick)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview
@Composable
fun DriversPreview() {
    DriversDirectoryTheme {
        Surface {
            DriversScreenContent(driversUiState = DriversViewModel.DriversUiState(
                listOf(
                    Driver(
                        id = 0,
                        name = "John"
                    ),
                    Driver(
                        id = 2,
                        name = "Mike"
                    ), Driver(
                        id = 3,
                        name = "Johnson"
                    )
                )
            ), {}, {}) {}
        }
    }
}