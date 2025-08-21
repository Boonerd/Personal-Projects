package com.example.calmvibe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private val affirmations = listOf(
        "You are enough.",
        "Take it one step at a time.",
        "You are stronger than you know.",
        "Breathe, you've got this.",
        "Today is a fresh start."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalmVibeApp(affirmations)
        }
    }
}

@Composable
fun CalmVibeApp(affirmations: List<String>, modifier: Modifier = Modifier) {
    var affirmationText by remember { mutableStateOf("Tap for a positive vibe!") }
    var moodText by remember { mutableStateOf("Select your mood") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = affirmationText,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { affirmationText = affirmations[Random.nextInt(affirmations.size)] },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("New Affirmation")
        }
        Text(
            text = moodText,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { moodText = "Mood: Happy 😊" }) {
                Text("Happy")
            }
            Button(onClick = { moodText = "Mood: Neutral 😐" }) {
                Text("Neutral")
            }
            Button(onClick = { moodText = "Mood: Sad 😔" }) {
                Text("Sad")
            }
        }
        Button(
            onClick = { affirmationText = "Breathe in for 4 seconds, hold for 4, breathe out for 4." },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Breathing Exercise")
        }
    }
}