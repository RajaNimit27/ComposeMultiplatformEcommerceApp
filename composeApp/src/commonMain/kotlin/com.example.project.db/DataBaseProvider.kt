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

    fun addToFavourite(product: Products,isFavourite:Boolean){
        product.isFavourite = isFavourite
        if(isFavourite) {
            database.addProduct(product)
        }else{
            database.removeProduct(product)
        }
    }

    fun addtoCart(product: Products,isAddtoCart:Boolean){
        product.isAddtoCart = true
        if(isAddtoCart) {
            database.addProduct(product)
        }else{
            database.removeProduct(product)
        }
    }
}