package viewmodel


import data.Repository
import com.app.compose_navigation_mvvm_flow.utils.UiState
import data.ProductResponse
import data.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): BaseViewModel() {

    val _uiStateProductList = MutableStateFlow<UiState<ProductResponse?>>(UiState.Loading)
    val uiStateProductList: StateFlow<UiState<ProductResponse?>> = _uiStateProductList

    val _uiStateProductDetail = MutableStateFlow<UiState<Products?>>(UiState.Loading)
    val uiStateProductDetail: StateFlow<UiState<Products?>> = _uiStateProductDetail


    fun getProductList() = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateProductList) { repository.getProducts() }
    }

    fun getProductDetail(id: Int?) = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateProductDetail,) { repository.getProductDetail(id) }
    }

    fun addFavouriteProduct(){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
}