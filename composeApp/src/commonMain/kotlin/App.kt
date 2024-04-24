
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import themes.lightYellow
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(lightYellow)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    setTabs()
                }
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
                BottomNavigation(backgroundColor = lightYellow) {
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
        alwaysShowLabel = true,
        label = { Text(text=tab.options.title, color=Color.Black)},
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}