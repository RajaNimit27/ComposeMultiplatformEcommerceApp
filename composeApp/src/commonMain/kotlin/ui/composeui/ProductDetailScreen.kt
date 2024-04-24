package ui.composeui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        var titleValue = remember { mutableStateOf("") }
        val scrollstate = rememberScrollState()
        val navigator= LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                CustomToolbarScreen(navigator,title = titleValue.value, true)
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
                            titleValue.value = it.title
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
        var quantity = remember { mutableStateOf(1) }
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 60.dp)
            ) {
                product.images?.let { ImageSlider(it) }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = product.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Price: $${product.price}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Rating: ${product.rating}",
                        fontSize = 15.sp,
                       fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Description: ${product.description}",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Brand: ${product.brand}",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quantity: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (quantity.value > 1) {
                                quantity.value--
                            }
                        }
                    ) {
                        Text("-")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${quantity.value}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            quantity.value++
                        }
                    ) {
                        Text("+")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            // Add to cart logic here
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Add to Cart")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            // Buy now logic here
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Buy Now")
                    }
                }

            }
        }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(list: List<Any>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f) { list.size }
    HorizontalPager(
        modifier = Modifier,
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(0.dp),
        beyondBoundsPageCount = 0,
        pageSize = PageSize.Fill,
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        pageContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                KamelImage(
                    resource = asyncPainterResource(data = list[it]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    )
}


