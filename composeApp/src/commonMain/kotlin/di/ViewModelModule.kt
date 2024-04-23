package di

import viewmodel.MainViewModel
import org.koin.dsl.module


val viewModelModule= module {
    factory{ MainViewModel(get()) }
}


