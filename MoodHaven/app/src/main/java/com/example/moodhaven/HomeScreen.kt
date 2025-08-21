package com.example.moodhaven

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(navController: NavController, moodDao: MoodDao) {
    val moods by moodDao.getAllMoods().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("MoodHaven", fontSize = 24.sp)
        Button(onClick = { navController.navigate("add_edit/0") }) {
            Text("Add Mood")
        }
        LazyColumn {
            items(moods) { mood ->
                MoodItem(mood, onEdit = { navController.navigate("add_edit/${mood.id}") }, onDelete = { /* TODO: Delete logic */ })
            }
        }
    }
}

@Composable
fun MoodItem(mood: Mood, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("${mood.mood} - ${mood.date}", fontSize = 18.sp)
                Text(mood.notes, fontSize = 14.sp)
            }
            Row {
                Button(onClick = onEdit) { Text("Edit") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete) { Text("Delete") }
            }
        }
    }
}