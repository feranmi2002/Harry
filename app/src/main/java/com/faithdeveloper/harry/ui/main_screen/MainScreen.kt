package com.faithdeveloper.harry.ui.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.faithdeveloper.harry.R
import com.faithdeveloper.harry.data.ApiResult
import com.faithdeveloper.harry.data.Status
import com.faithdeveloper.harry.model.HarryCharacter
import com.faithdeveloper.harry.ui.details_screen.HOUSE
import com.faithdeveloper.harry.ui.details_screen.NAME
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    onClickCharacter: (HarryCharacter) -> Unit,
    newSearch: (query: String) -> Unit,
    mainScreenViewModel: MainScreenViewModel,
    onSearchTypeChange: (type: String) -> Unit,
    retry: () -> Unit
) {

    var inputValue by remember { mutableStateOf(TextFieldValue()) }

    var searchType by remember {
        mutableStateOf(NAME)
    }

    val apiResult: ApiResult by mainScreenViewModel.result.observeAsState(
        initial = ApiResult(
            Status.LOADING,
            emptyList()
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    modifier = Modifier.clickable {
                        mainScreenViewModel.getAllCharacters()
                    },
                    text = stringResource(id = R.string.all_characters),
                    style = MaterialTheme.typography.titleSmall,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.secondary
                )


            }


            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = inputValue,
                onValueChange = { inputValue = it },
                shape = RoundedCornerShape(16.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = colorResource(id = R.color.purple_200)
                    )
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = stringResource(id = R.string.search),
                        textAlign = TextAlign.Center
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions {
                    if (inputValue.text.isNotBlank()) {
                        newSearch.invoke(inputValue.text)
                    }
                }

            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = stringResource(id = R.string.search_by),
                    style = MaterialTheme.typography.bodyMedium
                )
                Chip(name = NAME, searchType = searchType, onToggleChange = {
                    searchType = if (it) NAME
                    else HOUSE
                    onSearchTypeChange.invoke(searchType)

                })
                Chip(name = HOUSE, searchType = searchType, onToggleChange = {
                    searchType = if (it) HOUSE
                    else NAME
                    onSearchTypeChange.invoke(searchType)
                })
            }

            when (apiResult.type) {
                Status.LOADING -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Status.ERROR -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { retry.invoke() },
                            modifier = Modifier.wrapContentSize(align = Alignment.Center)
                        ) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
                else -> {
                    apiResult.data?.let { characters ->
                        if (characters.isNotEmpty()) {

                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(top = 8.dp),
                                state = rememberLazyListState()
                            ) {

                                if (apiResult.data != null) {
                                    items(apiResult.data as List<HarryCharacter>) { character ->
                                        CharacterRow(
                                            character = character,
                                            onClickCharacter = onClickCharacter
                                        )
                                    }
                                }
                            }

                        } else {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center ,
                                text = stringResource(id = R.string.empty_result),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CharacterRow(character: HarryCharacter, onClickCharacter: (HarryCharacter) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClickCharacter.invoke(character)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center,
            placeholder = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            error = painterResource(id = R.drawable.ic_baseline_account_circle_24)
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = character.name,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun Chip(
    name: String,
    searchType: String,
    onToggleChange: (Boolean) -> Unit,
) {
    val isSelected by remember {
        mutableStateOf(false)
    }.apply {
        this.value = searchType == name
    }

    Surface(
        modifier = Modifier.padding(4.dp),
        tonalElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    ) {

        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onToggleChange(it)
                }
            )
        ) {

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
