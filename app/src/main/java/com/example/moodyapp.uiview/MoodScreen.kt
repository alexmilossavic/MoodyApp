package com.example.moodyapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodyapp.data.MoodEntry
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun MoodScreen(navController: NavController, viewModel: MoodViewModel) {
    val moods by viewModel.moods.collectAsState()
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var entryToDelete: MoodEntry? by remember { mutableStateOf(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Button(onClick = { navController.navigate("addMoodScreen") }, modifier = Modifier.fillMaxWidth()) {
            Text("Neuen Eintrag hinzufügen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(moods) { mood ->
                // Erkennung eines Long-Press zum Löschen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    entryToDelete = mood
                                    showDeleteConfirmation = true
                                }
                            )
                        }
                ) {
                    MoodEntryItem(mood)
                }
            }
        }
    }

    if (showDeleteConfirmation && entryToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Eintrag löschen") },
            text = { Text("Möchten Sie diesen Eintrag wirklich löschen?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        entryToDelete?.let { viewModel.deleteMoodEntry(it) }
                        showDeleteConfirmation = false
                    }
                ) {
                    Text("Löschen")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Abbrechen")
                }
            }
        )
    }
}

