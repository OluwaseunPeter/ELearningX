package com.app.miva.player.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.currentItem
import platform.AVFoundation.currentTime
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.seekToTime
import platform.AVKit.AVPlayerViewController
import platform.CoreGraphics.CGRect
import platform.CoreMedia.CMTimeGetSeconds
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.NSURL
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCATransactionDisableActions
import platform.UIKit.UIView
import platform.darwin.Float64

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun videoPlayer(modifier: Modifier, url: String): VideoPlayerControl {
    val player = remember { AVPlayer(uRL = NSURL.URLWithString(url)!!) }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true
    playerLayer.player = player

    val videoControl by remember { mutableStateOf(IOSVideoPlayerControl()) }
    videoControl.setVideoPlayer(player)

    UIKitView(
        factory = {
            avPlayerViewController.view
        },
        update = { view ->
            player.play()
            avPlayerViewController.player!!.play()
        },
        modifier = modifier.height(200.dp)
    )

    return videoControl
}

class IOSVideoPlayerControl : VideoPlayerControl {
    private var player: AVPlayer? = null

    override fun play() {
        player?.play()
    }

    override fun pause() {
        player?.pause()
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun seek(position: Int) {
        val seekTime = CMTimeMakeWithSeconds(position.toDouble(), 600)
        player?.seekToTime(seekTime)
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun currentPosition(): Int {
        val time = player?.currentItem?.currentTime()
        return time?.let { CMTimeGetSeconds(it).toInt() } ?: 0
    }

    fun setVideoPlayer(player: AVPlayer) {
        this.player = player
    }
}