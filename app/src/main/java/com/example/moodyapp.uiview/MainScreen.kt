package com.example.moodyapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moodyapp.uiview.CalendarScreen
import com.example.moodyapp.uiview.BottomNavScreen
import com.example.moodyapp.viewmodel.MoodViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavHostController, viewModel: MoodViewModel) {
    val bottomNavItems = listOf(
        BottomNavScreen.Mood,
        BottomNavScreen.Charts,
        BottomNavScreen.Suggestions,
        BottomNavScreen.Calendar
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        /** NavHost(
        navController = navController,
        startDestination = BottomNavScreen.Mood.route,
        modifier = Modifier.padding(innerPadding)
        ) {
        composable(BottomNavScreen.Mood.route) { MoodScreen(navController, viewModel) }
        composable(BottomNavScreen.Charts.route) { ChartsScreen(navController) }
        composable(BottomNavScreen.Suggestions.route) { SuggestionsScreen(navController) }
        composable(BottomNavScreen.Calendar.route) { CalendarScreen() } // ✅ NAVIGATION FIXED!
        }
        }
         **/
    }
}
