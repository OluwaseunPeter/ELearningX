package com.app.miva.core.db

import android.annotation.SuppressLint
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.app.miva.db.MivaDb

@SuppressLint
actual class DatabaseFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(MivaDb.Schema, context, dbName)
    }
}