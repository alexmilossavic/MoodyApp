package com.example.moodyapp.uiview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodyapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Navigiere nach 5 Sekunden zur Route "mainScreen"
    LaunchedEffect(Unit) {
        delay(5000L)
        navController.navigate("mainScreen") {
            // Entfernt den SplashScreen aus dem Backstack
            popUpTo("splashScreen") { inclusive = true }
        }
    }

    // Anzeige des SplashScreens mit einem Logo
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Hier wird das Logo angezeigt. (Falls 1500.dp zu groß ist, benutze eine moderate Größe, z.B. 300.dp)
            Image(
                painter = painterResource(id = R.drawable.gelb),
                contentDescription = "App Logo",
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
