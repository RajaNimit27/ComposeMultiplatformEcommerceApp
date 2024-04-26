package com.example.project.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.project.db.AppDatabase

actual class DatabaseDriverFactory(val context: Context) {
    actual fun createDriver(): SqlDriver? {
       return AndroidSqliteDriver(AppDatabase.Schema, context, "AppDatabase.db")
    }
}