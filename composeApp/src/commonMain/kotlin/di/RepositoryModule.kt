package di

import data.Repository
import org.koin.dsl.module


val repositoryModule = module {
    factory {  Repository(get()) }
}