package com.example.watchbase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.watchbase.R
import com.example.watchbase.ui.screens.designsystem.Heading

@Composable
fun Search(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(modifier = modifier)
        Heading(title = stringResource(id = R.string.title_top_searches))
        TopSearchList()
    }
}

@Composable
fun SearchBar(modifier: Modifier) {
    TextField(
        value = "",
        onValueChange = {

        },
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(bottom = 16.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = ""
            )
        },
        singleLine = true,
        shape = CircleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = colorResource(id = R.color.search_icon_tint_color),
            leadingIconColor = colorResource(id = R.color.search_icon_tint_color),
            backgroundColor = colorResource(id = R.color.search_field_background),
            placeholderColor = colorResource(id = R.color.search_icon_tint_color),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search)
            )
        }
    )
}

@Composable
fun TopSearchList() {

}