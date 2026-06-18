package com.poomani.tv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.poomani.tv.ui.viewmodel.TvViewModel

@Composable
fun SettingsScreen(navController: NavHostController, viewModel: TvViewModel = hiltViewModel()) {
    val backendUrl = viewModel.settingsDataStore.backendUrl.collectAsStateWithLifecycle(initialValue = "")
    val textState = remember { mutableStateOf("") }

    LaunchedEffect(backendUrl.value) {
        textState.value = backendUrl.value
    }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A0E)).padding(16.dp)) {
        Text("System Settings", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Backend Base URL") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = Color.White, focusedTextColor = Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { viewModel.updateUrl(textState.value) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text("Save Engine Adjustments", color = Color.White)
        }
    }
}
