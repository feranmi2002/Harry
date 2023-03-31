package com.faithdeveloper.harry.viewmodel

import androidx.lifecycle.*
import com.faithdeveloper.harry.data.ApiHelper
import com.faithdeveloper.harry.data.ApiResult
import com.faithdeveloper.harry.data.Status
import com.faithdeveloper.harry.model.HarryCharacter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainScreenViewModel(private val apiHelper: ApiHelper) : ViewModel() {


    private var selectedCharacter: HarryCharacter? = null

    private var searchType: String = NAME_SEARCH_TYPE

    private lateinit var query: String

    private lateinit var lastPerformedOperation: LAST_PERFORMED_OPERATION

    private var _result: MutableLiveData<ApiResult> = MutableLiveData()

    val result: LiveData<ApiResult> get() = _result

    init {
        getAllCharacters()
    }


    fun getAllCharacters() {
        lastPerformedOperation = LAST_PERFORMED_OPERATION.GET_ALL_CHARACTERS
        viewModelScope.launch {
            apiHelper.getAllCharacters().collect {
                _result.value = it
            }

        }
    }


    fun newSearch(query: String) {
        lastPerformedOperation = LAST_PERFORMED_OPERATION.SEARCH_CHARACTERS
        this.query = query
        viewModelScope.launch {
            if (searchType == NAME_SEARCH_TYPE) {
                apiHelper.getCharactersByName(query).collect {
                    _result.value = it
                }
            } else {
                apiHelper.getCharactersByHouse(query).collect {
                    _result.value = it
                }
            }
        }
    }

    fun setSelectedCharacter(selectedCharacter: HarryCharacter) {
        this.selectedCharacter = selectedCharacter
    }

    fun selectedCharacter() = selectedCharacter

    fun setSearchType(searchType: String) {
        this.searchType = searchType

    }

    fun retry() {
        when (lastPerformedOperation) {
            LAST_PERFORMED_OPERATION.GET_ALL_CHARACTERS -> getAllCharacters()
            else -> newSearch(query)
        }
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

        const val NAME_SEARCH_TYPE = "Name"
        const val HOUSE_SEARCH_TYPE = "House"
    }

    private enum class LAST_PERFORMED_OPERATION {
        GET_ALL_CHARACTERS,
        SEARCH_CHARACTERS
    }


}