package data

import com.app.compose_navigation_mvvm_flow.utils.UiState
import data.remote.RemoteDataSource
import data.remote.toResultFlow
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {

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

}