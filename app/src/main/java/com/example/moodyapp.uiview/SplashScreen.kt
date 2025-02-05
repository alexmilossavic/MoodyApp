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
    // Nach 5 Sekunden wird zur Hauptseite navigiert
    LaunchedEffect(key1 = true) {
        delay(5000L) // 5000 Millisekunden = 5 Sekunden
        navController.navigate("moodScreen") {
            popUpTo("splashScreen") { inclusive = true }
        }
    }

    // Oberfläche des Splash Screens mit weißem Hintergrund
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Das Logo wird 10-mal so groß angezeigt (1500.dp)
            Image(
                painter = painterResource(id = R.drawable.gelb),
                contentDescription = "App Logo",
                modifier = Modifier.size(1500.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
