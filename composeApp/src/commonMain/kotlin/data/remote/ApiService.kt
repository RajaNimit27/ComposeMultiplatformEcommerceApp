package data.remote

import data.Receipes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import utils.Constants

class ApiService(private val httpClient: HttpClient) {

    val recipes="recipes/"
    suspend fun getReceipes(): Receipes = httpClient.get("${Constants.BASE_URL}$recipes").body<Receipes>()
    suspend fun getReceipeDetails(id:Int?): Receipes.Recipe = httpClient.get("${Constants.BASE_URL}$recipes$id").body<Receipes.Recipe>()
}