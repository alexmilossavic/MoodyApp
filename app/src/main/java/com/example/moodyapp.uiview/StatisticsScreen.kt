package com.example.moodyapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun StatisticsScreen(navController: NavController, viewModel: MoodViewModel) {
    val moods by viewModel.moods.collectAsState()
    val totalEntries = moods.size
    // **Geändert:** Berechnung der Anzahl Einträge pro Stimmung (Emoji)
    val moodCounts = moods.groupingBy { it.mood }.eachCount()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Statistiken", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Gesamteinträge: $totalEntries", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        moodCounts.forEach { (mood, count) ->
            Text("$mood: $count", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Zurück")
        }
    }
}
