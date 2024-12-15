package com.app.miva.core.di

import app.cash.sqldelight.db.SqlDriver
import com.app.miva.core.db.DatabaseFactory
import org.koin.dsl.module

actual fun getPlatformModule()  = module {
    single<SqlDriver> { DatabaseFactory().create() }
}