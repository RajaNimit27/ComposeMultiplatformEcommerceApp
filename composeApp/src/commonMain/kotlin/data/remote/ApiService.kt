package data.remote

import data.ProductResponse
import data.Products
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import utils.Constants

class ApiService(private val httpClient: HttpClient) {

    val products="products?limit=100"
    val productsEndpoint="products/"
    suspend fun getProducts(): ProductResponse = httpClient.get("${Constants.BASE_URL}$products").body<ProductResponse>()
    suspend fun getProductDetails(id:Int?): Products = httpClient.get("${Constants.BASE_URL}$productsEndpoint$id").body<Products>()
}