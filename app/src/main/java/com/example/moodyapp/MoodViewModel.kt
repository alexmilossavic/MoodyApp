package com.example.moodyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodyapp.data.MoodDatabase
import com.example.moodyapp.data.MoodEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoodViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = MoodDatabase.getDatabase(application).moodEntryDao()

    private val _moods = MutableStateFlow<List<MoodEntry>>(emptyList())
    val moods = _moods.asStateFlow()

    init {
        viewModelScope.launch {
            dao.getAllMoods().collect { moodList ->
                _moods.value = moodList
            }
        }
    }

    fun addMood(mood: String, note: String) {
        viewModelScope.launch {
            dao.insertMood(MoodEntry(mood = mood, note = note))
        }
    }
}
