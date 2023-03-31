package com.faithdeveloper.harry.ui.main_screen

import androidx.compose.runtime.Composable
import com.faithdeveloper.harry.model.HarryCharacter
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel

@Composable
fun MainScreenRoute(
    mainScreenViewModel: MainScreenViewModel,
    onClickCharacter:(character:HarryCharacter) -> Unit
){
    mainScreenViewModel.getAllCharacters()
    MainScreen( onClickCharacter = onClickCharacter, newSearch = {query ->
        mainScreenViewModel.newSearch(query)
    }, mainScreenViewModel, onSearchTypeChange = {
        mainScreenViewModel.setSearchType(it)
    }, retry = {
        mainScreenViewModel.retry()
    })
}