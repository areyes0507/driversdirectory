package com.rodriguez.driversdirectory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rodriguez.driversdirectory.R

@Composable
fun DriversDirectoryTopBar(onSortClicked: (() -> Unit)? = null, showAction: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 70.dp)
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .padding(all = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = stringResource(R.string.drivers_directory_top_bar_title),
            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.weight(weight = 1f))
        if (showAction) {
            TextButton(onClick = onSortClicked ?: { }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Localized description")
            }

        }
    }
}