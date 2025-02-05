package com.example.moodyapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodyapp.uiview.SplashScreen
import com.example.moodyapp.ui.MoodScreen
import com.example.moodyapp.ui.AddMoodScreen
import com.example.moodyapp.ui.StatisticsScreen
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: MoodViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ) {
        // Splash Screen als Startdestination
        composable("splashScreen") {
            SplashScreen(navController = navController)
        }
        // Hauptseite der App (MoodScreen)
        composable("moodScreen") {
            MoodScreen(navController = navController, viewModel = viewModel)
        }
        // Screen zum Hinzufügen eines neuen Mood-Eintrags
        composable("addMoodScreen") {
            AddMoodScreen(navController = navController, viewModel = viewModel)
        }
        // Statistik-Screen
        composable("statisticsScreen") {
            StatisticsScreen(navController = navController, viewModel = viewModel)
        }
    }
}

