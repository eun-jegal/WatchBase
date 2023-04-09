package com.example.watchbase.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    onNavigateToDetailScreen: (Show) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
    ) {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))

        TopSearchList(
            showList = searchViewModel.trendingShows.value,
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
    }
}

@Composable
fun SearchBar() {
    BasicTextField(
        value = "",
        onValueChange = {},
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
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "",
                    tint = Color.LightGray
                )
                Text(
                    text = stringResource(id = R.string.search),
                    fontSize = 18.sp,
                    color = Color.LightGray
                )
            }
        })
}

@Composable
fun TopSearchList(
    showList: List<Show>,
    onNavigateToDetailScreen: (Show) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Heading(title = stringResource(id = R.string.title_top_searches))
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(showList) { show ->
                SearchResult(show = show, onNavigateToDetailScreen = {
                    onNavigateToDetailScreen(it)
                })
            }
        }
    }
}

@Composable
fun SearchResult(
    show: Show,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.85f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = "${BuildConfig.BASE_BACKDROP_IMAGE_URL}${show.backdropPath}",
                contentScale = ContentScale.FillHeight,
                contentDescription = show.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxHeight()
            )
            Text(
                text = show.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "View Detail",
            modifier = Modifier.clickable { onNavigateToDetailScreen(show) },
            tint = Color.White
        )
    }
}