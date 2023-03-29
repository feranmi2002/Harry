package com.faithdeveloper.harry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.faithdeveloper.harry.data.ApiHelper
import com.faithdeveloper.harry.data.MainScreenPagingSource

class MainScreenViewModel(private val apiHelper: ApiHelper): ViewModel() {

    fun getAllCharacters() = Pager(
        PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            MainScreenPagingSource(apiHelper)
        }
    ).flow.cachedIn(viewModelScope)

    fun newSearch(query: String) {

    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            apiHelper: ApiHelper,
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainScreenViewModel(apiHelper) as T
                }
            }

        const val PAGE_SIZE = 40
    }


}