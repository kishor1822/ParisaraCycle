package com.example.parisara_cycle.stats

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "eco_stats")

class StatsRepository(private val context: Context) {
    private val TOTAL_CO2_SAVED = doublePreferencesKey("total_co2_saved")

    val totalCo2Saved: Flow<Double> = context.dataStore.data
        .map { preferences ->
            preferences[TOTAL_CO2_SAVED] ?: 0.0
        }

    suspend fun addDistance(distanceKm: Double) {
        val co2Saved = distanceKm * 120 // 1km = 120g CO2
        context.dataStore.edit { preferences ->
            val currentTotal = preferences[TOTAL_CO2_SAVED] ?: 0.0
            preferences[TOTAL_CO2_SAVED] = currentTotal + co2Saved
        }
    }
}
