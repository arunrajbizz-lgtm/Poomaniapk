package com.poomani.tv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.poomani.tv.ui.components.GlassmorphicCard
import com.poomani.tv.ui.viewmodel.TvViewModel

@Composable
fun SeriesScreen(navController: NavHostController, viewModel: TvViewModel = hiltViewModel()) {
    val seriesList = viewModel.series.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A0E)).padding(16.dp)) {
        Text("TV Series", style = MaterialTheme.typography.headlineMedium, color = Color.White, modifier = Modifier.padding(bottom = 16.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(seriesList.value) { series ->
                GlassmorphicCard(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                    Text(series.title, color = Color.White, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
