package ui.paging

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Products
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun ProductCard(list: List<Products>, onProductClick: (Products) -> Unit) {
       Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
           ImageSliderHomeBanner(bannerImages()) }
             LazyVerticalGrid(
                   columns = GridCells.Fixed(2),
                   verticalArrangement = Arrangement.spacedBy(5.dp),
                   horizontalArrangement = Arrangement.spacedBy(5.dp),
                   modifier = Modifier.fillMaxSize(),
                   content = {
                       items(list.size) { index ->
                           val data = list[index]
                           ProductItem(data,onProductClick)
                       }
                   }
               )

  }



@Composable
fun ProductItem(products: Products,onProductClick: (Products) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight().clickable { onProductClick(products) },
        shape = MaterialTheme.shapes.medium,
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 5.dp)) {
        Column(modifier = Modifier.height(350.dp).background(Color.White).padding(10.dp)) {
            KamelImage(
                resource = asyncPainterResource(data = products.thumbnail),
                contentDescription = null,
                modifier = Modifier.height(150.dp).fillMaxWidth().clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    modifier = Modifier.padding(2.dp),
                    fontSize = 14.sp,
                    text = products.title,
                    maxLines = 3,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = products.description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "$${products.price}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "${products.discountPercentage}% discount",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(red=0.1f, green = 0.8f, blue = 0.0f)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderHomeBanner(list: List<Any>) {
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
            Card(
                shape = CardDefaults.elevatedShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp)
                    .height(150.dp)
                    .background(Color.Transparent),
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

fun bannerImages():List<String>{
    return listOf("https://img.freepik.com/premium-vector/weekend-sale-banner-template-with-liquid-shapes_85212-187.jpg","https://img.freepik.com/premium-vector/flat-travel-sale-background_23-2149048750.jpg","https://img.freepik.com/premium-photo/photocomposition-horizontal-shopping-banner-with-woman-big-smartphone_23-2151201773.jpg","https://img.freepik.com/premium-vector/digital-marketing-concept-shopping-online-mobile-application_68971-366.jpg")
}