package ui.composeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app.compose_navigation_mvvm_flow.ui.composeui.SimpleAlertDialog
import com.app.compose_navigation_mvvm_flow.utils.UiState
import data.Products
import viewmodel.MainViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


class ProductDetailScreen(val mainViewModel: MainViewModel, val id: Int?):Screen {
    @Composable
    override fun Content() {
        val scrollstate = rememberScrollState()
        val navigator= LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                CustomToolbarScreen(navigator,title = "Details", true)
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollstate)
                    .padding(2.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //add your code
                LaunchedEffect(key1 = Unit) {
                    getProductDetail(mainViewModel, id)
                }
                val state = mainViewModel.uiStateProductDetail.collectAsState()
                when (state.value) {
                    is UiState.Success -> {
                        ProgressLoader(isLoading = false)
                        (state.value as UiState.Success<Products>).data?.let {
                          //  RecipeDetailView(recipe = it)
                            ProductDetail(navigator,it)
                        }
                    }

                    is UiState.Loading -> {
                        ProgressLoader(isLoading = true)
                    }

                    is UiState.Error -> {
                        ProgressLoader(isLoading = false)
                        //Handle Error
                        SimpleAlertDialog(message =  ((state.value as UiState.Error<Products>).message))
                    }
                }
            }
        }
    }

}

private fun getProductDetail(mainViewModel: MainViewModel, id: Int?) {
    // Call the function to fetch recipes
    mainViewModel.getProductDetail(id)
}


    @Composable
    fun ProductDetail(navigator: Navigator, product: Products) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = product.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Price: $${product.price}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Rating: ${product.rating}",
                    )
                }
                Text(
                    text = "Description: ${product.description}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Brand: ${product.brand}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Category: ${product.category}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    product.images?.forEach { imageUrl ->
                        KamelImage(
                            resource = asyncPainterResource(data = imageUrl),
                            contentDescription = null,
                            modifier = Modifier.height(150.dp).fillMaxWidth().clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }

}


