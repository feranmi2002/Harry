package com.faithdeveloper.harry.ui.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.faithdeveloper.harry.R


@Composable
fun DetailsScreen(character: Map<String, String>, onClickBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onClickBack.invoke()
                    },
                alignment = Alignment.CenterStart,
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                textAlign = TextAlign.Center,
                text = character[NAME]!!,
                style = MaterialTheme.typography.titleLarge
            )
        }
        AsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(bottom = 8.dp),
            placeholder = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            model = character[IMAGE],
            contentDescription = null,
            error = painterResource(id = R.drawable.ic_baseline_account_circle_24)

        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(character.toList()) { character ->
                DetailsRow(title = character.first, item = character.second)
            }
        }
    }
}


@Composable
fun DetailsRow(title: String?, item: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(top = 4.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title ?: "",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item?:"",
            style = MaterialTheme.typography.titleSmall
        )
    }
}