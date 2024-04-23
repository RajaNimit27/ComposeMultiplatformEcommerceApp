package data.remote



class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getProducts() = apiService.getProducts()
    suspend fun getProductDetail(id:Int?) = apiService.getProductDetails(id)
}