package com.app.miva.core.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.app.miva.db.MivaDb

actual class DatabaseFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(MivaDb.Schema, dbName)
    }
}