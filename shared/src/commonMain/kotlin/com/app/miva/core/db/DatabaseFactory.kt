package com.app.miva.core.db

import app.cash.sqldelight.db.SqlDriver

const val dbName = "miva_kmp.db"

expect class DatabaseFactory {
    fun create(): SqlDriver
}