package com.example.moodyapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun AddMoodScreen(navController: NavController, viewModel: MoodViewModel) {
    var selectedMood by remember { mutableStateOf("😊") }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Wie fühlst du dich heute?", style = MaterialTheme.typography.titleMedium)

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            listOf("😊", "😢", "😡", "😴", "😍").forEach { mood ->
                Text(
                    text = mood,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { selectedMood = mood }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Wofür bist du dankbar?", style = MaterialTheme.typography.titleMedium)

        BasicTextField(
            value = note,
            onValueChange = { note = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addMood(selectedMood, note.text)
            navController.popBackStack()
        }) {
            Text("Speichern")
        }
    }
}

// Vorschau mit Fake ViewModel (kein Application-Kontext erforderlich)
@Preview(showBackground = true)
@Composable
fun AddMoodScreenPreview() {
    val fakeViewModel = object : MoodViewModel(null) {
        override fun addMood(mood: String, note: String) {
            // Keine echte Logik erforderlich für die Vorschau
        }
    }
    AddMoodScreen(navController = rememberNavController(), viewModel = fakeViewModel)
}

