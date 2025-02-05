package com.example.moodyapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodyapp.data.MoodEntry
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun MoodScreen(navController: NavController, viewModel: MoodViewModel) {
    val moods by viewModel.moods.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("addMoodScreen") }) {
            Text("Neuen Eintrag hinzufügen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(moods) { mood ->
                MoodItem(mood)
            }
        }
    }
}

@Composable
fun MoodItem(mood: MoodEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Stimmung: ${mood.mood}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Notiz: ${mood.note}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}


