
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import di.appModule
import org.koin.compose.KoinApplication
import ui.tabs.CartTab
import ui.tabs.FavouriteTab
import ui.tabs.HomeTab
import ui.tabs.ProfileTab

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        MaterialTheme {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
               setTabs()
            }
        }
    }

}

@Composable
fun setTabs() {
    TabNavigator(
        HomeTab,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(HomeTab, FavouriteTab, CartTab, ProfileTab)
            )
        }
    ) { tabNavigator ->
        Scaffold(
            content = {
                CurrentTab()
            },
            bottomBar = {
                BottomNavigation(backgroundColor = Color.White) {
                    TabNavigationItem(HomeTab)
                    TabNavigationItem(FavouriteTab)
                    TabNavigationItem(CartTab)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}