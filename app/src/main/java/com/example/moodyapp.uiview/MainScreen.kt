package com.example.moodyapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moodyapp.R
import com.example.moodyapp.uiview.BottomNavScreen
import com.example.moodyapp.viewmodel.MoodViewModel
import com.example.moodyapp.uiview.CalendarScreen
import com.example.moodyapp.ui.ChartsScreen
import com.example.moodyapp.ui.MoodScreen
import com.example.moodyapp.ui.SuggestionsScreen

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
            NavigationBar(
                containerColor = Color(0xFFFFC107) // ✅ Bottom Bar in Senfgelb
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title,
                                tint = Color.Black // ✅ Icons in Schwarz
                            )
                        },
                        label = { Text(screen.title, color = Color.Black) }, // ✅ Text in Schwarz
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // ✅ Hintergrund ist jetzt Weiß
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ✅ App-Logo in der Mitte
            Image(
                painter = painterResource(id = R.drawable.gelb),
                contentDescription = "App Logo",
                modifier = Modifier.size(800.dp), // Logo-Größe anpassen
                contentScale = ContentScale.Fit
            )
        }
    }
}


