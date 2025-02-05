package com.example.moodyapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(moodEntry: MoodEntry)

    @Query("SELECT * FROM mood_entries ORDER BY date DESC")
    fun getAllMoods(): Flow<List<MoodEntry>>

    @Delete
    suspend fun deleteMood(moodEntry: MoodEntry)  // Methode zum Löschen eines Eintrags
}
