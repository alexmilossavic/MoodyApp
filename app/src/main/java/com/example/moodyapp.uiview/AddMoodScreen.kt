package com.example.moodyapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun AddMoodScreen(navController: NavController, viewModel: MoodViewModel) {
    var selectedMood by remember { mutableStateOf("😊") }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Wie fühlst du dich heute?", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("😊", "😢", "😡", "😴", "😍").forEach { mood ->
                Text(
                    text = mood,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { selectedMood = mood }
                )
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

        Button(onClick = {
            viewModel.addMood(selectedMood, note.text)
            navController.popBackStack()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Speichern")
        }
    }
}
