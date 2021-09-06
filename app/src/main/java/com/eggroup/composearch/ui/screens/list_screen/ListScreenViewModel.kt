package com.eggroup.composearch.ui.screens.list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eggroup.composearch.network.ApiState
import com.eggroup.composearch.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)
    init {
        getPost()
    }
    private fun getPost() = viewModelScope.launch {
        repository.getPost()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Failure(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
    }
}