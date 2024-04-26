package com.example.project.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.project.db.AppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver? {
        return NativeSqliteDriver(AppDatabase.Schema, "AppDatabase.db")
    }
}