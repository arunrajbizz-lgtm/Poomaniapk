package com.poomani.tv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.poomani.tv.ui.components.GlassmorphicCard
import com.poomani.tv.ui.navigation.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LiveTvScreen(navController: NavHostController) {
    val sampleChannels = listOf("Demo HLS stream")
    val testStream = "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8"

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0F0F14)).padding(16.dp)) {
        Text("Live TV Channels", color = Color.White, modifier = Modifier.padding(bottom = 16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sampleChannels.size) { index ->
                GlassmorphicCard(
                    modifier = Modifier.fillMaxWidth().height(120.dp).clickable {
                        val encodedUrl = URLEncoder.encode(testStream, StandardCharsets.UTF_8.toString())
                        navController.navigate(Screen.Player.createRoute(encodedUrl))
                    }
                ) {
                    Text(sampleChannels[index], color = Color.White)
                }
            }
        }
    }
}
