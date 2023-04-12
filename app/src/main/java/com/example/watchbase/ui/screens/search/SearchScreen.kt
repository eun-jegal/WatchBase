package com.example.watchbase.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.screens.designsystem.SearchBar
import com.example.watchbase.ui.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    onNavigateToDetailScreen: (Show) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchQuery = searchViewModel.searchQuery.value
    val searchResult = searchViewModel.searchResult.value.collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
    ) {
        SearchBar(searchViewModel = searchViewModel, doSearch = { searchViewModel.search() })
        Spacer(modifier = Modifier.height(16.dp))

        if (searchQuery.isEmpty()) {
            TopSearchList(
                showList = searchViewModel.topSearchShows.value,
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
        }
        if (searchResult.itemCount > 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                when (searchResult.loadState.refresh) {
                    is LoadState.NotLoading -> {
                        items(searchResult.itemCount) { index: Int ->
                            searchResult[index]?.let { show ->
                                SearchItem(
                                    show = show,
                                    onNavigateToDetailScreen = { onNavigateToDetailScreen(show) })
                            }
                        }
                    }
                }
            }
        }
    }
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

@Composable
fun SearchItem(
    show: Show,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    AsyncImage(
        model = "${BuildConfig.BASE_POSTER_IMAGE_URL}${show.posterPath}",
        contentScale = ContentScale.FillBounds,
        contentDescription = "Show",
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                onNavigateToDetailScreen(show)
            }
    )
}