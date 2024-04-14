package data.remote



class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getReceipes() = apiService.getReceipes()
    suspend fun getReceipesDetail(id:Int?) = apiService.getReceipeDetails(id)
}