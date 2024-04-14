package di

import com.app.compose_navigation_mvvm_flow.viewmodels.MainViewModel
import org.koin.dsl.module


val viewModelModule= module {
    factory{ MainViewModel(get()) }
}


