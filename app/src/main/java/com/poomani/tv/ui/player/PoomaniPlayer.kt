package com.poomani.tv.ui.player

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.datasource.okhttp.OkHttpDataSource
import okhttp3.OkHttpClient

@OptIn(UnstableApi::class)
class PoomaniPlayer(private val context: Context, okHttpClient: OkHttpClient) {
    
    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context)
        .setMediaSourceFactory(
            androidx.media3.exoplayer.source.DefaultMediaSourceFactory(context)
                .setDataSourceFactory(OkHttpDataSource.Factory(okHttpClient))
        )
        .build()

    fun playStream(url: String) {
        val mimeType = when {
            url.contains(".m3u8") -> MimeTypes.APPLICATION_M3U8
            url.contains(".mpd") -> MimeTypes.APPLICATION_MPD
            else -> MimeTypes.APPLICATION_SS
        }
        val mediaItem = MediaItem.Builder()
            .setUri(url)
            .setMimeType(mimeType)
            .build()
            
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun release() {
        exoPlayer.release()
    }
}
