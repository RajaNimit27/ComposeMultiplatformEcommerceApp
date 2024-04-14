package com.app.compose_navigation_mvvm_flow.viewmodels


import data.Receipes
import data.Repository
import com.app.compose_navigation_mvvm_flow.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import viewmodel.BaseViewModel

class MainViewModel(private val repository: Repository): BaseViewModel() {

    val _uiStateReceipeList = MutableStateFlow<UiState<Receipes?>>(UiState.Loading)
    val uiStateReceipeList: StateFlow<UiState<Receipes?>> = _uiStateReceipeList

    val _uiStateReceipeDetail = MutableStateFlow<UiState<Receipes.Recipe?>>(UiState.Loading)
    val uiStateReceipeDetail: StateFlow<UiState<Receipes.Recipe?>> = _uiStateReceipeDetail


    fun getReceipesList() = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateReceipeList) { repository.getReceipes() }
    }

    fun getReceipeDetail(id: Int?) = CoroutineScope(Dispatchers.IO).launch {
        fetchData(_uiStateReceipeDetail,) { repository.getReceipesDetail(id) }
    }
}