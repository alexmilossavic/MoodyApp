package com.example.moodyapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodyapp.viewmodel.MoodViewModel

@Composable
fun AddMoodScreen(navController: NavController, viewModel: MoodViewModel) {
    var selectedMood by remember { mutableStateOf("😊") }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Wie fühlst du dich heute?", style = MaterialTheme.typography.titleMedium)

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            listOf("😊", "😢", "😡", "😴", "😍").forEach { mood ->
                Text(text = mood, fontSize = MaterialTheme.typography.headlineLarge.fontSize, modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedMood = mood })
            }
        }

        BasicTextField(value = note, onValueChange = { note = it }, modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            viewModel.addMood(selectedMood, note.text)
            navController.popBackStack()
        }) {
            Text("Speichern")
        }
    }
}
