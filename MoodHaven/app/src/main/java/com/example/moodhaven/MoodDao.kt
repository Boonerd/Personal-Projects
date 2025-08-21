package com.example.moodhaven

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Query("SELECT * FROM moods")
    fun getAllMoods(): Flow<List<Mood>>

    @Insert
    suspend fun insert(mood: Mood)

    @Update
    suspend fun update(mood: Mood)

    @Delete
    suspend fun delete(mood: Mood)
}