package com.example.project.db

object DataBaseProviderObject {
    lateinit var databaseDriverFactory:DatabaseDriverFactory
    private val databaseSql: Database by lazy {
        // Initialize your Database instance here
        // Example:
        Database(databaseDriverFactory)
    }

    fun getDatabase(): Database {
        return databaseSql
    }
    fun setDriveFactory(databaseDriverFactory:DatabaseDriverFactory){
        this.databaseDriverFactory= databaseDriverFactory
    }
}