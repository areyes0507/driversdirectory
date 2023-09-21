package com.rodriguez.driversdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.rodriguez.driversdirectory.ui.navigation.DriversDirectoryNavGraph
import com.rodriguez.driversdirectory.ui.theme.DriversDirectoryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DriversDirectoryApplication()
        }
    }

    @Composable
    fun DriversDirectoryApplication() {
        val navController = rememberNavController()
        DriversDirectoryTheme {
            DriversDirectoryNavGraph(navHostController = navController)
        }
    }
}