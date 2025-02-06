package com.example.moodyapp.uiview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface BottomNavScreen {
    val route: String
    val title: String
    val icon: ImageVector

    data object Mood : BottomNavScreen {
        override val route = "moodScreen"
        override val title = "Mood"
        override val icon = Icons.Filled.Home
    }

    data object Charts : BottomNavScreen {
        override val route = "chartsScreen"
        override val title = "Charts"
        override val icon = Icons.Filled.ShowChart
    }

    data object Suggestions : BottomNavScreen {
        override val route = "suggestionsScreen"
        override val title = "Suggestions"
        override val icon = Icons.Filled.MusicNote
    }

    data object Calendar : BottomNavScreen {
        override val route = "calendarScreen"
        override val title = "Calendar"
        override val icon = Icons.Filled.CalendarToday
    }
}
