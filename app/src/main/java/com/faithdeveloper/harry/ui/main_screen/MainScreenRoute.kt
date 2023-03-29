package com.faithdeveloper.harry.ui.main_screen

import androidx.compose.runtime.Composable
import com.faithdeveloper.harry.model.Characters
import com.faithdeveloper.harry.ui.theme.MainScreen
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel

@Composable
fun MainScreenRoute(
    mainScreenViewModel: MainScreenViewModel,
    onClickCharacter:(character:Characters) -> Unit
){
    MainScreen(characters = mainScreenViewModel.getAllCharacters(), onClickCharacter = onClickCharacter, newSearch = {query ->
        mainScreenViewModel.newSearch(query)
    })
}