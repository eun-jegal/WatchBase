package com.example.watchbase.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.watchbase.R
import com.example.watchbase.data.model.Genre
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.ui.screens.designsystem.Chip
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.viewmodel.HomeViewModel

const val BASE_POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

@Composable
fun Home(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        TVShowOrMovieChips(homeViewModel = homeViewModel, modifier = modifier)
        GenreList(viewModel = homeViewModel, modifier = modifier)
        ShowList(homeViewModel = homeViewModel, modifier = modifier)
    }
}

@Composable
fun ShowList(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Column() {
        HeadingAndCarousel(
            showList = homeViewModel.trendingShows.value.collectAsLazyPagingItems(),
            title = stringResource(id = R.string.title_top_rated),
            modifier = modifier
        )
        HeadingAndCarousel(
            showList = homeViewModel.popularShows.value.collectAsLazyPagingItems(),
            title = stringResource(id = R.string.title_popular),
            modifier = modifier
        )
        HeadingAndCarousel(
            showList = homeViewModel.topRatedShows.value.collectAsLazyPagingItems(),
            title = stringResource(id = R.string.title_popular),
            modifier = modifier
        )
        HeadingAndCarousel(
            showList = homeViewModel.nowPlayingShows.value.collectAsLazyPagingItems(),
            title = stringResource(id = R.string.title_popular),
            modifier = modifier
        )
        if (homeViewModel.selectedShowType.value == ShowType.MOVIE) {
            HeadingAndCarousel(
                showList = homeViewModel.upcomingMovies.value.collectAsLazyPagingItems(),
                title = stringResource(id = R.string.title_popular),
                modifier = modifier
            )
        }
    }
}

@Composable
fun TVShowOrMovieChips(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(color = Color.Black)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val selectedType = homeViewModel.selectedShowType.value
            val typeList = listOf(ShowType.TV_SHOW, ShowType.MOVIE)
            typeList.forEach { showType ->
                Chip(
                    selected = true,
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
        }
    }
}

@Composable
fun GenreList(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.genres),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(viewModel.genres) { index, genre ->
                if (index == 0) {
                    Chip(selected = true, text = genre.name, onClick = {})
                } else {
                    Chip(selected = false, text = genre.name, onClick = {})
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeadingAndCarousel(
    showList: LazyPagingItems<Show>,
    title: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Heading(title = title)
        Spacer(modifier = modifier.height(12.dp))
        when (showList.loadState.refresh) {
            is LoadState.NotLoading -> {
                LazyRow {
                    items(showList) { show ->
                        show?.let {
                            Log.d("Eun", "show: " + it.title)
                            val imagePath = "$BASE_POSTER_IMAGE_URL${it.posterPath}"
                            GlideImage(
                                model = imagePath,
                                contentDescription = "movie",
                                modifier = modifier
                                    .width(160.dp)
                                    .height(300.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}