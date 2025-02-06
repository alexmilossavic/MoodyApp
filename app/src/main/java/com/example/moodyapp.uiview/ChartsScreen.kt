package com.example.moodyapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChartsScreen(navController: NavController) {
    // Beispiel-Daten: Anzahl der Mood-Einträge (z. B. für die letzten 7 Tage)
    val sampleData = listOf(5, 3, 7, 2, 8, 4, 6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Mood Chart", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Balkendiagramm
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val maxVal = sampleData.maxOrNull() ?: 1
            val barWidth = size.width / (sampleData.size * 2)
            sampleData.forEachIndexed { index, value ->
                val barHeight = (value / maxVal.toFloat()) * size.height
                drawRect(
                    color = Color(0xFFFFC107),
                    topLeft = Offset(
                        x = (index * 2 * barWidth) + barWidth / 2,
                        y = size.height - barHeight
                    ),
                    size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text("Mood Heatmap", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Einfache Heatmap: 4 Wochen x 7 Tage (Zellen, deren Intensität variiert)
        val weeks = 4
        val days = 7
        Column {
            for (week in 0 until weeks) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (day in 0 until days) {
                        val intensity = (((week * days + day) % 10) / 10f).coerceAtLeast(0.2f)
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color(0xFFFFC107).copy(alpha = intensity))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Zurück zum MainScreen Button
        Button(
            onClick = { navController.navigate("mainScreen") }, // ✅ Navigiert zum MainScreen
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zurück zum Hauptmenü")
        }
    }
}
