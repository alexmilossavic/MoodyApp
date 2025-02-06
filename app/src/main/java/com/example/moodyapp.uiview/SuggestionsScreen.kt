package com.example.moodyapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class MoodSuggestion(
    val mood: String,
    val musicSuggestion: String,
    val meditationSuggestion: String,
    val motivationalQuote: String
)

@Composable
fun SuggestionsScreen(navController: NavController) {
    // Beispiel-Daten für jeden Smiley
    val suggestions = listOf(
        MoodSuggestion(
            mood = "😊",
            musicSuggestion = "Happy Hits Playlist",
            meditationSuggestion = "Guided Meditation for Joy",
            motivationalQuote = "Keep smiling, life is beautiful!"
        ),
        MoodSuggestion(
            mood = "😢",
            musicSuggestion = "Soothing Acoustic",
            meditationSuggestion = "Calm Meditation for Tears",
            motivationalQuote = "After every storm comes a rainbow."
        ),
        MoodSuggestion(
            mood = "😡",
            musicSuggestion = "Anger Management Beats",
            meditationSuggestion = "Mindfulness Meditation for Anger",
            motivationalQuote = "Channel your anger into positive energy."
        ),
        MoodSuggestion(
            mood = "😴",
            musicSuggestion = "Relaxing Sleep Sounds",
            meditationSuggestion = "Sleep Meditation",
            motivationalQuote = "Rest is the best meditation."
        ),
        MoodSuggestion(
            mood = "😍",
            musicSuggestion = "Romantic Ballads",
            meditationSuggestion = "Loving Kindness Meditation",
            motivationalQuote = "Love yourself and the rest will follow."
        )
    )
    var selectedMood by remember { mutableStateOf("😊") }
    var currentSuggestion by remember { mutableStateOf(suggestions.first { it.mood == selectedMood }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Mood Suggestions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Smiley-Auswahl
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            suggestions.forEach { suggestion ->
                Text(
                    text = suggestion.mood,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.clickable {
                        selectedMood = suggestion.mood
                        currentSuggestion = suggestion
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        // Anzeige der Vorschläge für den ausgewählten Smiley
        Text("Music Suggestion: ${currentSuggestion.musicSuggestion}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Meditation Suggestion: ${currentSuggestion.meditationSuggestion}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Motivational Quote: ${currentSuggestion.motivationalQuote}", style = MaterialTheme.typography.bodyLarge)

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
