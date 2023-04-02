package com.example.watchbase.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.ui.screens.designsystem.Chip
import com.example.watchbase.ui.screens.designsystem.DropDownMenu
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToGenreScreen: () -> Unit,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SelectionChips(
            homeViewModel = homeViewModel,
            onNavigateToGenreScreen = onNavigateToGenreScreen
        )
        ShowList(
            homeViewModel = homeViewModel,
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
    }
}

@Composable
fun SelectionChips(
    homeViewModel: HomeViewModel,
    onNavigateToGenreScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val selectedType = homeViewModel.selectedShowType.value
        val typeList = listOf(ShowType.TV_SHOW, ShowType.MOVIE)
        typeList.forEach { showType ->
            Chip(
                selected = selectedType == showType,
                text = stringResource(id = if (showType == ShowType.TV_SHOW) R.string.category_tv_show else R.string.category_movies),
                onClick = {
                    if (selectedType != showType) {
                        homeViewModel.run {
                            selectedShowType.value = showType
                            fetchData(genreId = null)
                        }
                    }
                })
        }
        DropDownMenu(
            text = homeViewModel.selectedGenre.value.name,
            onClick = { onNavigateToGenreScreen() }
        )
    }
}

@Composable
fun ShowList(
    homeViewModel: HomeViewModel,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    Column {
        HeadingAndCarousel(
            showList = homeViewModel.trendingShows.value.collectAsLazyPagingItems(),
            onNavigateToDetailScreen = onNavigateToDetailScreen,
            title = stringResource(id = R.string.title_trending)
        )
        HeadingAndCarousel(
            showList = homeViewModel.topRatedShows.value.collectAsLazyPagingItems(),
            onNavigateToDetailScreen = onNavigateToDetailScreen,
            title = stringResource(id = R.string.title_top_rated)
        )
        HeadingAndCarousel(
            showList = homeViewModel.popularShows.value.collectAsLazyPagingItems(),
            onNavigateToDetailScreen = onNavigateToDetailScreen,
            title = stringResource(id = R.string.title_popular)
        )
        HeadingAndCarousel(
            showList = homeViewModel.nowPlayingShows.value.collectAsLazyPagingItems(),
            onNavigateToDetailScreen = onNavigateToDetailScreen,
            title = stringResource(id = R.string.title_now_playing)
        )
        if (homeViewModel.selectedShowType.value == ShowType.MOVIE) {
            HeadingAndCarousel(
                showList = homeViewModel.upcomingMovies.value.collectAsLazyPagingItems(),
                onNavigateToDetailScreen = onNavigateToDetailScreen,
                title = stringResource(id = R.string.title_upcoming)
            )
        }
    }
}

@Composable
fun HeadingAndCarousel(
    showList: LazyPagingItems<Show>,
    onNavigateToDetailScreen: (Show) -> Unit,
    title: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Heading(title = title)
        Spacer(modifier = Modifier.height(12.dp))
        when (showList.loadState.refresh) {
            is LoadState.NotLoading -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(showList) { show ->
                        show?.let {
                            val imagePath = "${BuildConfig.BASE_POSTER_IMAGE_URL}${it.posterPath}"
                            AsyncImage(
                                model = imagePath,
                                contentScale = ContentScale.FillHeight,
                                contentDescription = "Show",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .width(150.dp)
                                    .clickable {
                                        onNavigateToDetailScreen(it)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}