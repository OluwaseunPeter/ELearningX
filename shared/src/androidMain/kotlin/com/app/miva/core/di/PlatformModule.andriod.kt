package com.app.miva.core.di

import app.cash.sqldelight.db.SqlDriver
import com.app.miva.core.db.DatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun getPlatformModule() = module {
//    single<VideoCache>{ AndroidVideoCache(androidContext()) }
    single<SqlDriver> { DatabaseFactory(androidContext()).create() }
}