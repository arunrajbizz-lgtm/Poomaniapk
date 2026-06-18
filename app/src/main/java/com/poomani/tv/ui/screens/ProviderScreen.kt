package com.poomani.tv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProviderScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0F0F14)).padding(16.dp)) {
        Text("Providers", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Connected safely. Privacy secured (Credentials Hidden).", color = Color.Gray)
    }
}
