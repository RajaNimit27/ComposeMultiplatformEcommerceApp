package data

import com.app.compose_navigation_mvvm_flow.utils.UiState
import com.example.project.db.DataBaseProviderObject
import data.remote.RemoteDataSource
import data.remote.toResultFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(private val remoteDataSource: RemoteDataSource) {

    val dataBase= DataBaseProviderObject.getDatabase()

    suspend fun getProducts(): Flow<UiState<ProductResponse?>> {
        return toResultFlow {
            remoteDataSource.getProducts()
        }
    }

    suspend fun getProductDetail(id:Int?): Flow<UiState<Products?>> {
        return toResultFlow{
            remoteDataSource.getProductDetail(id)
        }
    }

    fun addFavouriteProduct(products: Products,isFavourite:Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            products.isFavourite = isFavourite
            dataBase.addProduct(products)
        }
    }

    fun addtoCartProduct(products: Products){
        CoroutineScope(Dispatchers.IO).launch {
            products.isAddtoCart = true
            dataBase.addProduct(products)
        }
    }

    fun removeCartProduct(products: Products){
        CoroutineScope(Dispatchers.IO).launch {
            dataBase.removeProduct(products)
        }
    }

    suspend fun getFavouriteProducts(): List<Products> {
        return withContext(Dispatchers.IO) {
            val cachedProductsDeferred = async { dataBase.getAllFavourites() }
            val cachedProducts = cachedProductsDeferred.await()
            cachedProducts
        }
    }

    suspend fun getCartProducts(): List<Products> {
        return withContext(Dispatchers.IO) {
            val cachedProductsDeferred = async { dataBase.getAllCartProducts() }
            val cachedProducts = cachedProductsDeferred.await()
            cachedProducts
        }
    }

}