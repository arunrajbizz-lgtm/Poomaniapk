package com.poomani.tv.ui.screens

import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.poomani.tv.ui.player.PoomaniPlayer
import okhttp3.OkHttpClient

@Composable
fun PlayerScreen(streamUrl: String, navController: NavHostController) {
    val context = LocalContext.current
    val okHttpClient = remember { OkHttpClient() }
    val poomaniPlayer = remember { PoomaniPlayer(context, okHttpClient) }

    DisposableEffect(streamUrl) {
        poomaniPlayer.playStream(streamUrl)
        onDispose {
            poomaniPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = poomaniPlayer.exoPlayer
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
