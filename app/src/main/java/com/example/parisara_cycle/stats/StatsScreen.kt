package com.example.parisara_cycle.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun StatsScreen(repository: StatsRepository) {
    val totalCo2Saved by repository.totalCo2Saved.collectAsState(initial = 0.0)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Eco-Stats", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total CO2 Saved: ${String.format(Locale.getDefault(), "%.2f", totalCo2Saved)}g",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Monthly Total", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "${String.format(Locale.getDefault(), "%.2f", totalCo2Saved)}g",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = "Keep cycling for a greener planet!", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
