package com.app.miva

import androidx.compose.ui.window.ComposeUIViewController
import com.app.miva.core.di.initKoin
import com.app.miva.core.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }