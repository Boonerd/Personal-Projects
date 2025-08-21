package com.example.moodhaven

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddEditScreen(navController: NavController, moodDao: MoodDao, moodId: Int?) {
    var mood by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Add/Edit Mood", fontSize = 24.sp)
        TextField(
            value = mood,
            onValueChange = { mood = it },
            label = { Text("Mood (Happy, Neutral, Sad)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            coroutineScope.launch {
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                if (moodId == null || moodId == 0) {
                    moodDao.insert(Mood(mood = mood, notes = notes, date = date))
                } else {
                    moodDao.update(Mood(id = moodId, mood = mood, notes = notes, date = date))
                }
                navController.popBackStack()
            }
        }) {
            Text("Save")
        }
    }
}