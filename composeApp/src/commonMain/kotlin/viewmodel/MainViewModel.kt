package viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import data.Repository
import com.app.compose_navigation_mvvm_flow.utils.UiState
import data.ProductResponse
import data.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: Repository): BaseViewModel() {

    val _uiStateProductList = MutableStateFlow<UiState<ProductResponse?>>(UiState.Loading)
    val uiStateProductList: StateFlow<UiState<ProductResponse?>> = _uiStateProductList

    val _uiStateProductDetail = MutableStateFlow<UiState<Products?>>(UiState.Loading)
    val uiStateProductDetail: StateFlow<UiState<Products?>> = _uiStateProductDetail
    private val _favouriteProducts = mutableStateOf<List<Products>>(emptyList())
    val favouriteProducts: State<List<Products>> = _favouriteProducts

    private val _cartProducts = mutableStateOf<List<Products>>(emptyList())
    val cartProducts: State<List<Products>> = _cartProducts

    // Function to fetch favourite products


    fun getProductList() = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateProductList) { repository.getProducts() }
    }

    fun getProductDetail(id: Int?) = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateProductDetail,) { repository.getProductDetail(id) }
    }

    fun addFavouriteProduct(products: Products,isFavourite:Boolean){
        repository.addFavouriteProduct(products,isFavourite)
    }

    fun addtoCartProduct(products: Products){
        repository.addtoCartProduct(products)
    }

    fun removeProduct(products: Products){
        repository.removeCartProduct(products)
    }

    fun getFavouriteProducts() {
       CoroutineScope(Dispatchers.IO).launch {
             _favouriteProducts.value = repository.getFavouriteProducts()
        }
    }

    fun getCartProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            _cartProducts.value = repository.getCartProducts()
        }
    }
}