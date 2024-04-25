package com.example.project.db

import data.Products

class DataBaseProvider(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class) suspend fun getProducts(): List<Products> {
        val cachedProducts = database.getAllProducts()
        return cachedProducts
    }

    @Throws(Exception::class) suspend fun getCachedProductsExists(): Boolean{
        val cachedProducts = database.getAllProducts()
        return cachedProducts.isNotEmpty()
    }

    fun clearDataBase(){
        database.clearDatabase()
    }

    fun insertProducts(list: List<Products>){
        database.createProducts(list)
    }
}