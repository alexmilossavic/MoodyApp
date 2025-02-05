package com.example.moodyapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_entries")
data class MoodEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mood: String,
    val note: String,
    val date: Long = System.currentTimeMillis()
)
