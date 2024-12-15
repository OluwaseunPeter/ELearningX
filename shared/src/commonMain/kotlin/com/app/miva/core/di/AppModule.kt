package com.app.miva.core.di

import com.app.miva.chapters.di.chapterModule
import com.app.miva.player.di.playerModule
import com.orda.shared.data.api.getDefaultClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

val coreModules = module {
    single { getDefaultClient() }
}

fun appModule() = listOf(coreModules, getPlatformModule(), chapterModule, playerModule)


fun initKoin () {
    startKoin {
        modules(appModule())
    }
}