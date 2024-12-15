package com.app.miva.player.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun videoPlayer(modifier: Modifier, url: String) : VideoPlayerControl


interface VideoPlayerControl {
    fun play()
    fun pause()
    fun seek(position:Int)
    fun currentPosition(): Int
}