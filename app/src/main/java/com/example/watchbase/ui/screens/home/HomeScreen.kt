package com.example.watchbase.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.ui.screens.designsystem.ActionButton
import com.example.watchbase.ui.screens.designsystem.Chip
import com.example.watchbase.ui.screens.designsystem.DropDownMenu
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToGenreScreen: () -> Unit,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SelectionChips(
            homeViewModel = homeViewModel,
            onNavigateToGenreScreen = onNavigateToGenreScreen
        )
        TrendingShows(
            homeViewModel = homeViewModel,
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
        ShowList(
            homeViewModel = homeViewModel,
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingShows(
    homeViewModel: HomeViewModel,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    val shows = homeViewModel.trendingShows.value
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            HorizontalPager(count = shows.size, state = pagerState) { page ->
                val show = shows[page]
                AsyncImage(
                    model = "${BuildConfig.BASE_POSTER_IMAGE_URL}${show.posterPath}",
                    contentScale = ContentScale.FillWidth,
                    contentDescription = show.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .clickable {
                            onNavigateToDetailScreen(show)
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth().height(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(shows.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(5.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
        ) {
            ActionButton(imageVector = Icons.Default.Info,
                label = "View Details",
                contentColor = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                    .clickable { onNavigateToDetailScreen(shows[pagerState.currentPage]) })
            Spacer(modifier = Modifier.width(8.dp))
            ActionButton(imageVector = Icons.Default.Add,
                label = "Add to List",
                contentColor = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                    .clickable { })
        }
        Spacer(modifier = Modifier.height(24.dp))
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Heading(title = title)
        }
        Spacer(modifier = Modifier.height(12.dp))
        when (showList.loadState.refresh) {
            is LoadState.NotLoading -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    itemsIndexed(showList) { index: Int, show: Show? ->
                        show?.let {
                            AsyncImage(
                                model = "${BuildConfig.BASE_POSTER_IMAGE_URL}${it.posterPath}",
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