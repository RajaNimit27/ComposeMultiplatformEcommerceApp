package com.example.project.db

import data.Products
import org.example.project.db.AppDatabase

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = databaseDriverFactory.createDriver()?.let { AppDatabase(it) }
    private val dbQuery = database?.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery?.transaction {
            dbQuery.removeAllProducts()
        }
    }

    internal fun getAllProducts(): List<Products> {
        return dbQuery?.selectAllProducts(::mapLaunchSelecting)?.executeAsList() ?: emptyList()
    }

    private fun mapLaunchSelecting(
        id: Long?,
        title: String?,
        description: String?,
        price: Double?,
        discountPercentage: Double?,
        category: String?,
        thumbnail: String?,
        isAddtoCart:Boolean?,
        isFavourite:Boolean?
    ): Products {
        return Products(
            id = (id?:0).toInt(),
            title = title?:"",
            description = description?:"",
            price = price?:0.0,
            discountPercentage = discountPercentage?:0.0,
            category = category?:"",
            thumbnail = thumbnail?:"",
            isAddtoCart = isAddtoCart,
            isFavourite = isFavourite
        )
    }

    internal fun createProducts(products: List<Products>) {
        dbQuery?.transaction {
            products.forEach { product ->
                insertProduct(product)
            }
        }
    }

    internal fun addProduct(product: Products){
        dbQuery?.transaction {
            insertProduct(product)
        }
    }

    internal fun removeProduct(products: Products){
        dbQuery?.deleteProduct(products.id.toLong())
    }



    private fun insertProduct(product: Products) {
        dbQuery?.insertProduct(
            id = product.id.toLong(),
            title = product.title,
            description = product.description,
            price = product.price,
            discountPercentage = product.discountPercentage,
            category = product.category,
            thumbnail = product.thumbnail,
            isAddedToCart = product.isAddtoCart,
            isFavourite = product.isFavourite
        )
    }
}