package com.example.moodyapp.ui

import android.os.Bundle
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
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

@Composable
fun MoodScreen(navController: NavController, viewModel: MoodViewModel) {
    val moods by viewModel.moods.collectAsState()
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var entryToDelete: MoodEntry? by remember { mutableStateOf(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("addMoodScreen") }) {
            Text("Neuen Eintrag hinzufügen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(moods) { mood ->
                var isDeleted by remember { mutableStateOf(false) }

                // Swipe to delete logic using simple gestures
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
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
                    if (isDeleted) {
                        // Show that the item was deleted (you can add animation or any effects here)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.2f))
                        )
                    }

                    MoodEntryItem(mood)
                }
            }
        }
    }

    // Bestätigungsdialog zum Löschen eines Eintrags
    if (showDeleteConfirmation && entryToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Möchten Sie diesen Eintrag löschen?") },
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


