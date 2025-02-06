package com.example.moodyapp.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun AddMoodScreen(navController: NavController, viewModel: MoodViewModel) {
    // **Geändert:** Verwendung einer mutableStateListOf zur Personalisierung der Icons.
    val moodIcons = remember { mutableStateListOf("😊", "😢", "😡", "😴", "😍") }
    var selectedMood by remember { mutableStateOf(moodIcons.first()) }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    // Zustände für den Icon-Anpassungsdialog
    var iconToEditIndex by remember { mutableIntStateOf(-1) }
    var showIconEditDialog by remember { mutableStateOf(false) }
    var newIconText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Wie fühlst du dich heute?", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // **Geändert:** Iteration über moodIcons mit Erkennung von Tap und Long‑Press.
            moodIcons.forEachIndexed { index, moodIcon ->
                // Erkennung von Tap (Auswahl) und Long‑Press (Personalisierung)
                Box(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { selectedMood = moodIcon },
                            onLongPress = {
                                iconToEditIndex = index
                                newIconText = moodIcon
                                showIconEditDialog = true
                            }
                        )
                    }
                ) {
                    Text(
                        text = moodIcon,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mehrzeiliges Eingabefeld mit Label
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = { Text("Wofür bist du dankbar?") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.addMood(selectedMood, note.text)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Speichern")
        }
    }

    // **Geändert:** Dialog zur Anpassung eines Icons.
    if (showIconEditDialog) {
        AlertDialog(
            onDismissRequest = { showIconEditDialog = false },
            title = { Text("Icon anpassen") },
            text = {
                OutlinedTextField(
                    value = newIconText,
                    onValueChange = { newIconText = it },
                    label = { Text("Neues Icon (Emoji)") }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (iconToEditIndex in moodIcons.indices) {
                            moodIcons[iconToEditIndex] = newIconText
                            // Falls das bearbeitete Icon gerade ausgewählt war, wird auch die Auswahl aktualisiert.
                            if (selectedMood == moodIcons[iconToEditIndex]) {
                                selectedMood = newIconText
                            }
                        }
                        showIconEditDialog = false
                    }
                ) {
                    Text("Übernehmen")
                }
            },
            dismissButton = {
                TextButton(onClick = { showIconEditDialog = false }) {
                    Text("Abbrechen")
                }
            }
        )
    }
}
