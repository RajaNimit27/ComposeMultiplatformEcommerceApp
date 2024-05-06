package ui.composeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app.compose_navigation_mvvm_flow.ui.composeui.SimpleAlertDialog
import com.app.compose_navigation_mvvm_flow.utils.UiState
import data.ProductResponse
import org.koin.compose.getKoin
import viewmodel.MainViewModel

object ProfileScreen: Screen {

    @Composable
    override fun Content() {
        val mainViewModel: MainViewModel = getKoin().get()
        val navigator= LocalNavigator.currentOrThrow
        Scaffold(topBar = {
            CustomToolbarScreen(navigator, title = "Profile", false)
        })
        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //add your code

            }
        }
    }


}