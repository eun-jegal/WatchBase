package com.example.watchbase.ui.screens.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchbase.R
import com.example.watchbase.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    searchViewModel: SearchViewModel,
    doSearch: () -> Unit
) {
    var searchQuery: String by remember { mutableStateOf("") }

    LaunchedEffect(key1 = searchQuery) {
        if (searchViewModel.searchQuery.value.trim().isNotEmpty()) {
            delay(750)
            doSearch()
        }
    }

    BasicTextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = if (it.trim().isNotEmpty()) it else ""
            searchViewModel.searchQuery.value = searchQuery
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .background(
                color = colorResource(id = R.color.search_field_background),
                shape = CircleShape
            ),
        singleLine = true,
        decorationBox = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = "",
                        tint = Color.LightGray
                    )
                    Text(
                        text = searchQuery.ifEmpty { stringResource(id = R.string.search) },
                        fontSize = 18.sp,
                        color = Color.LightGray
                    )
                }
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear the text field",
                        tint = Color.LightGray,
                        modifier = Modifier.clickable {
                            searchQuery = ""
                        }
                    )
                }
            }
        })
}