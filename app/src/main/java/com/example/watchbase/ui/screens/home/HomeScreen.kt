package com.example.watchbase.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.ui.screens.designsystem.Chip
import com.example.watchbase.ui.screens.designsystem.DropDownMenu
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
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
        HeroSection(
            homeViewModel = homeViewModel,
            onNavigateToGenreScreen = onNavigateToGenreScreen,
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
fun HeroSection(
    homeViewModel: HomeViewModel,
    onNavigateToGenreScreen: () -> Unit,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.app_background))
            .fillMaxWidth()
    ) {
        val shows = homeViewModel.trendingShows.value
        val pagerState = rememberPagerState()

        TrendingShowsInPager(
            shows = shows,
            pagerState = pagerState,
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )

        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 8.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            SelectionChips(
                homeViewModel = homeViewModel,
                onNavigateToGenreScreen = onNavigateToGenreScreen
            )
            TrendingShowPageIndicator(showSize = shows.size, pagerState = pagerState)
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingShowsInPager(
    shows: List<Show>,
    pagerState: PagerState,
    onNavigateToDetailScreen: (Show) -> Unit
) {
    val imageSize = remember { mutableStateOf(IntSize.Zero) }
    HorizontalPager(count = shows.size, state = pagerState) { page ->
        val show = shows[page]
        Box {
            AsyncImage(
                model = "${BuildConfig.BASE_POSTER_IMAGE_URL}${show.posterPath}",
                contentScale = ContentScale.Crop,
                contentDescription = show.title,
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned {
                        imageSize.value = it.size
                    }
            )

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = imageSize.value.height.toFloat() / 2,  // 1/3
                            endY = imageSize.value.height.toFloat()
                        )
                    )
                    .clickable {
                        onNavigateToDetailScreen(show)
                    },
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(start = 48.dp, bottom = 24.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    val colonIndex = show.title.indexOf(':')
                    val spaceIndex = show.title.indexOf(' ', colonIndex)
                    val title = if (colonIndex != -1 && spaceIndex != -1) "${
                        show.title.substring(
                            0,
                            colonIndex
                        )
                    }\n${show.title.substring(spaceIndex + 1)}" else show.title
                    Text(
                        text = title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                //ToDO: Genre
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingShowPageIndicator(
    showSize: Int,
    pagerState: PagerState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(showSize) { iteration ->
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