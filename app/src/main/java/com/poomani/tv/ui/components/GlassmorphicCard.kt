package com.poomani.tv.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlassmorphicCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        Surface(
            modifier = Modifier.matchParentSize().blur(25.dp),
            color = Color.White.copy(alpha = 0.07f),
            shape = RoundedCornerShape(16.dp)
        ) {}
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}
