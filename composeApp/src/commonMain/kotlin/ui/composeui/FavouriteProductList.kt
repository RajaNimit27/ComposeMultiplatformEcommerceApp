package ui.composeui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.project.db.DatabaseProvider
import data.Products
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.getKoin
import themes.lightYellow
import ui.tabs.CartTab
import viewmodel.MainViewModel


object FavouriteProductList:Screen {

    @Composable
    override fun Content() {
        val navigator= LocalNavigator.currentOrThrow
        val mainViewModel:MainViewModel = getKoin().get()
        val favouriteProducts = remember(mainViewModel) { mainViewModel.favouriteProducts }
        Scaffold(
            topBar = {
                CustomToolbarScreen(navigator,title = "Favourites", false)
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(2.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //add your code
                LaunchedEffect(key1 = Unit) {
                    mainViewModel.getFavouriteProducts()
                }
                LazyColumn {
                    items(favouriteProducts.value.size) {
                        ProductItem(favouriteProducts.value[it])
                    }
                }
            }
        }
    }

}

@Composable
fun ProductItem(product: Products) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 5.dp)) {
            Row(modifier = Modifier.fillMaxSize(1f).background(lightYellow), verticalAlignment = Alignment.CenterVertically) {
                KamelImage(
                    resource = asyncPainterResource(data = product.thumbnail),
                    contentDescription = null,
                    modifier = Modifier.height(100.dp).width(100.dp).padding(horizontal = 5.dp).clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
                Column (
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = product.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                    Text(text = "Price: $${product.price}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(text = "Rating: ${product.rating}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(text = "Description: ${product.description}",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 15.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
}



