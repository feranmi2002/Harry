package com.faithdeveloper.harry.viewmodel

import androidx.lifecycle.*
import com.faithdeveloper.harry.data.ApiHelper
import com.faithdeveloper.harry.data.ApiResult
import com.faithdeveloper.harry.model.HarryCharacter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainScreenViewModel(val apiHelper: ApiHelper) : ViewModel() {

    //  stores the character clicked by the user.it is passed to the detail screen by the nav graph
    private lateinit var selectedCharacter: HarryCharacter

    //stores the search type: Either search by "house" or "name"
    private var searchType: String = NAME_SEARCH_TYPE

    //    stores the search query entered by the user
    private lateinit var query: String

    // used to track the last task requested by the ui. In case of failure, this flag is used
//    to know which task to perform when the retry button is clicked on the ui
    private lateinit var lastPerformedOperation: LAST_PERFORMED_OPERATION

    //    unexposed result of api network call
    private var _result: MutableLiveData<ApiResult> = MutableLiveData()

    //    exposed result of api network call
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

    //    stores the selected character by the user
    fun setSelectedCharacter(selectedCharacter: HarryCharacter) {
        this.selectedCharacter = selectedCharacter
    }

    //    get the selected character
    fun selectedCharacter() = selectedCharacter

    //    set search type
    fun setSearchType(searchType: String) {
        this.searchType = searchType

    }

    //  retry the failed network api call
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

    //    this is used to know which task to perform when retry is clicked on the ui
    private enum class LAST_PERFORMED_OPERATION {
        GET_ALL_CHARACTERS,
        SEARCH_CHARACTERS
    }
}