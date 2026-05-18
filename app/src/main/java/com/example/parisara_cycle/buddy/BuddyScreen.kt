package com.example.parisara_cycle.buddy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Buddy(val name: String, val distance: String)

@Composable
fun BuddyScreen() {
    val buddies = remember {
        listOf(
            Buddy("Rahul", "500m away"),
            Buddy("Priya", "1.2km away"),
            Buddy("Amit", "2km away")
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Cycling Buddies Nearby", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(buddies) { buddy ->
                BuddyItem(buddy)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /* Share location logic */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Share My Location")
        }
    }
}

@Composable
fun BuddyItem(buddy: Buddy) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = buddy.name, style = MaterialTheme.typography.titleMedium)
                Text(text = buddy.distance, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /* Request to cycle together */ }) {
                Text("Connect")
            }
        }
    }
}
