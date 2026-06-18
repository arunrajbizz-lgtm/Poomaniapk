package com.poomani.tv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.poomani.tv.ui.components.GlassmorphicCard
import com.poomani.tv.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {
    val menus = listOf(
        "Live TV" to Screen.LiveTv.route,
        "Movies" to Screen.Movies.route,
        "Series" to Screen.Series.route,
        "Providers" to Screen.Providers.route,
        "Global Search" to Screen.Search.route,
        "Settings" to Screen.Settings.route
    )

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A0E)).padding(16.dp)) {
        Text("PoomaniTV", style = MaterialTheme.typography.headlineLarge, color = Color.White, modifier = Modifier.padding(bottom = 24.dp))
        Text("Discover Content", style = MaterialTheme.typography.titleMedium, color = Color.LightGray, modifier = Modifier.padding(bottom = 12.dp))
        
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(menus) { menu ->
                GlassmorphicCard(modifier = Modifier.size(150.dp, 110.dp).clickable { navController.navigate(menu.second) }) {
                    Text(menu.first, color = Color.White, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
