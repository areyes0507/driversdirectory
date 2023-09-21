package com.rodriguez.driversdirectory.ui.drivers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodriguez.driversdirectory.domain.model.Driver
import com.rodriguez.driversdirectory.ui.theme.DriversDirectoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverItem(driver: Driver, onDriverClick: (Driver) -> Unit) {
    Card(border = BorderStroke(width = 1.dp, color = Color.Cyan),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ), onClick = {
            onDriverClick(driver)
        }) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Name: ${driver.name}")
            Text(text = "Id: ${driver.id}")
        }
    }
}

@Preview
@Composable
fun DriverItemPreview() {
    DriversDirectoryTheme {
        Surface {
            DriverItem(driver = Driver(
                id = 0,
                name = "John"
            ), onDriverClick = {})
        }
    }
}