package com.example.moodyapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.moodyapp.ui.AddMoodScreen
import com.example.moodyapp.ui.MoodScreen
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: MoodViewModel = viewModel()

    NavHost(navController, startDestination = "moodScreen") {
        composable("moodScreen") { MoodScreen(navController, viewModel) }
        composable("addMoodScreen") { AddMoodScreen(navController, viewModel) }
    }
}
