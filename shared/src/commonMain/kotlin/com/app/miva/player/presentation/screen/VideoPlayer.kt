package com.app.miva.player.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(modifier: Modifier, url: String)