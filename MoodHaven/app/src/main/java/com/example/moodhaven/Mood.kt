package com.example.moodhaven

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mood: String,
    val notes: String,
    val date: String
)