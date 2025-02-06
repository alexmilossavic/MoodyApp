package com.example.moodyapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodyapp.ui.AddMoodScreen
import com.example.moodyapp.ui.ChartsScreen
import com.example.moodyapp.ui.MainScreen
import com.example.moodyapp.ui.MoodScreen
import com.example.moodyapp.ui.StatisticsScreen
import com.example.moodyapp.ui.SuggestionsScreen
import com.example.moodyapp.uiview.SplashScreen
import com.example.moodyapp.uiview.CalendarScreen
import com.example.moodyapp.uiview.BottomNavScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: MoodViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ) {
        composable("splashScreen") {
            SplashScreen(navController = navController)
        }
        composable("mainScreen") {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable("addMoodScreen") {
            AddMoodScreen(navController = navController, viewModel = viewModel)
        }
        composable("statisticsScreen") {
            StatisticsScreen(navController = navController, viewModel = viewModel)
        }
        composable("calendarScreen") {
            CalendarScreen(navController = navController) // ✅ FIXED: Jetzt mit navController
        }
        composable("chartsScreen") {
            ChartsScreen(navController = navController)
        }
        composable("moodScreen") {
            MoodScreen(navController = navController, viewModel = viewModel)
        }
        composable("suggestionsScreen") {
            SuggestionsScreen(navController = navController)
        }
    }
}

