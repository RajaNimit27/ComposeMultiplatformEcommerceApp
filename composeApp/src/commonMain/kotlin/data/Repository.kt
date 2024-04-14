package data

import com.app.compose_navigation_mvvm_flow.utils.UiState
import data.remote.RemoteDataSource
import data.remote.toResultFlow
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getReceipes(): Flow<UiState<Receipes?>> {
        return toResultFlow(){
            remoteDataSource.getReceipes()
        }
    }

    suspend fun getReceipesDetail(id:Int?): Flow<UiState<Receipes.Recipe?>> {
        return toResultFlow(){
            remoteDataSource.getReceipesDetail(id)
        }
    }

}