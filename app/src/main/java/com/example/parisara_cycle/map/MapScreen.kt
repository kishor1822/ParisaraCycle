package com.example.parisara_cycle.map

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parisara_cycle.stats.StatsRepository
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun MapScreen(repository: StatsRepository) {
    val singapore = LatLng(1.35, 103.87) // Default location
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f)
    }

    var dangerZones by remember { mutableStateOf(listOf<LatLng>()) }
    val pitStops = remember {
        listOf(
            LatLng(1.36, 103.88) to "Cycle Repair Shop",
            LatLng(1.34, 103.86) to "Water Point"
        )
    }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false,
                mapType = MapType.NORMAL
            ),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true),
            onMapLongClick = { latLng ->
                dangerZones = dangerZones + latLng
            }
        ) {
            dangerZones.forEach { zone ->
                Marker(
                    state = MarkerState(position = zone),
                    title = "Danger Zone",
                    snippet = "Reported by user",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                )
            }

            pitStops.forEach { (pos, title) ->
                Marker(
                    state = MarkerState(position = pos),
                    title = title,
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Long press to pin a Danger Zone")
            Button(onClick = {
                scope.launch {
                    repository.addDistance(1.0) // Simulate 1km
                }
            }) {
                Text("Simulate 1km Cycle")
            }
        }
    }
}
