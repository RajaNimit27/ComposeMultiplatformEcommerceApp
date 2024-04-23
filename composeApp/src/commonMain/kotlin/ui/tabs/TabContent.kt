package ui.tabs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TabContent(screen:Screen) {

    Navigator(screen) { navigator ->
        SlideTransition(navigator) { screen ->
            Column {
                screen.Content()
            }
        }
    }
}

