package com.app.miva.player.presentation.screen

import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun videoPlayer(modifier: Modifier , url: String) : VideoPlayerControl {
    val videoControl by remember { mutableStateOf(AndroidVidePlayerControl()) }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            VideoView(context).apply {
                setVideoPath(url)
                val mediaController = MediaController(context)
                mediaController.setAnchorView(this)
                setMediaController(mediaController)
                start()
                videoControl.setVideoView(this)
            }
        },
        update = {}
    )
    return videoControl
}


class AndroidVidePlayerControl : VideoPlayerControl {
    private  var videoView: VideoView? = null
    override fun play() {
        videoView?.start()
    }

    override fun pause() {
        videoView?.pause()
    }

    override fun seek(position: Int) {
        videoView?.seekTo(position)
    }

    override fun currentPosition(): Int {
        return videoView?.currentPosition ?: 0
    }

    fun setVideoView(videoView: VideoView) {
        this.videoView = videoView
    }
}