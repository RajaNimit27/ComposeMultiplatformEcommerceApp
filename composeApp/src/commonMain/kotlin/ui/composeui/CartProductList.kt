package ui.composeui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.getKoin
import themes.lightYellow
import viewmodel.MainViewModel


object CartProductList:Screen {

    @Composable
    override fun Content() {
        val navigator= LocalNavigator.currentOrThrow
        val mainViewModel:MainViewModel = getKoin().get()
        val cartProducts = remember(mainViewModel) { mainViewModel.cartProducts }
        val totalCost= remember { mutableStateOf(0.0) }
        var refreshData = remember { mutableStateOf(true) }

        Scaffold(

            topBar = {
                CustomToolbarScreen(navigator,title = "Cart", false)
            }
        )
        { innerPadding ->
            totalCost.value = cartProducts.value.sumOf { it.price }
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(2.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //add your code
                LaunchedEffect(key1 = refreshData) {
                    mainViewModel.getCartProducts()
                    refreshData.value= false


                }
                Box(modifier = Modifier.fillMaxSize()){
                    LazyColumn {
                        items(cartProducts.value.size) {
                           // totalCost.value += cartProducts.value[it].price
                            ProductItem(cartProducts.value[it]){
                                mainViewModel.removeProduct(cartProducts.value[it])
                                refreshData.value = true
                            }
                        }
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth().height(100.dp).align(Alignment.BottomCenter).padding(20.dp).background(
                            lightYellow),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text("Total Cost", fontSize = 15.sp, modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Medium)

                        Text(fontSize = 15.sp, text = "$"+totalCost.value.toString(), modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Medium)
                    }

                }

            }
        }
    }

}



