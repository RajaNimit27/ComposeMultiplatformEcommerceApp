package ui.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ui.composeui.HomeScreen

object CartTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.ShoppingCart)
            return remember {
                TabOptions(index = 0u, title = "Cart", icon = icon)
            }
        }

    @Composable
    override fun Content() {
        TabContent(HomeScreen)
    }
}