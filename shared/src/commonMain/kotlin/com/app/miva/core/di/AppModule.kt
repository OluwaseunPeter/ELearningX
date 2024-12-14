package com.app.miva.core.di

import com.app.miva.chapters.di.chapterModule
import org.koin.core.context.startKoin

fun appModule() = listOf(chapterModule)


fun initKoin () {
    startKoin {
        modules(appModule())
    }
}