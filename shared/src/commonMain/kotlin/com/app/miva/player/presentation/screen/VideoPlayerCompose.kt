package com.app.miva.player.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val VIDEO_URL = "VIDEO_URL"

@Composable
fun VideoPlayerCompose(videoUrl: String) {
    return Column(modifier = Modifier.fillMaxSize()
        .padding(vertical = 12.dp, horizontal = 14.dp)) {
        VideoPlayer(url = videoUrl, modifier = Modifier.fillMaxWidth())
    }
}