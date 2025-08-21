package com.example.moodhaven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moodDao = MoodDatabase.getDatabase(this).moodDao()
        setContent {
            MoodHavenApp(moodDao)
        }
    }
}

@Composable
fun MoodHavenApp(moodDao: MoodDao) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, moodDao) }
        composable("add_edit/{moodId}") { backStackEntry ->
            AddEditScreen(navController, moodDao, backStackEntry.arguments?.getString("moodId")?.toIntOrNull())
        }
    }
}