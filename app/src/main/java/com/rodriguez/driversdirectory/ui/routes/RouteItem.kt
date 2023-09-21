package com.rodriguez.driversdirectory.ui.routes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodriguez.driversdirectory.R
import com.rodriguez.driversdirectory.domain.model.Route
import com.rodriguez.driversdirectory.ui.theme.DriversDirectoryTheme

@Composable
fun RouteItem(route: Route) {
    Card(border = BorderStroke(width = 1.dp, color = Color.Cyan),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = stringResource(R.string.routes_name_format, route.name))
            Text(text = stringResource(R.string.routes_type_format, route.type))
            Text(text = stringResource(R.string.routes_id_format, route.id))
        }
    }
}

@Preview
@Composable
fun DriverItemPreview() {
    DriversDirectoryTheme {
        Surface {
            RouteItem(route = Route(
                name = "Route 1",
                type = "R",
                id = 0
            ))
        }
    }
}