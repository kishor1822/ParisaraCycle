package com.example.parisara_cycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parisara_cycle.buddy.BuddyScreen
import com.example.parisara_cycle.map.MapScreen
import com.example.parisara_cycle.stats.StatsRepository
import com.example.parisara_cycle.stats.StatsScreen
import com.example.parisara_cycle.ui.theme.ParisaracycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statsRepository = StatsRepository(applicationContext)
        enableEdgeToEdge()
        setContent {
            ParisaracycleTheme {
                MainScreen(statsRepository)
            }
        }
    }
}

@Composable
fun MainScreen(statsRepository: StatsRepository) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Map") },
                    label = { Text("Map") },
                    selected = currentRoute == "map",
                    onClick = { navController.navigate("map") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Stats") },
                    label = { Text("Stats") },
                    selected = currentRoute == "stats",
                    onClick = { navController.navigate("stats") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Buddy") },
                    label = { Text("Buddy") },
                    selected = currentRoute == "buddy",
                    onClick = { navController.navigate("buddy") }
                )
            }
        }
    ) {
innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "map",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("map") { MapScreen(statsRepository) }
            composable("stats") { StatsScreen(statsRepository) }
            composable("buddy") { BuddyScreen() }
        }
    }
}
