package com.example.watchbase.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Cast
import com.example.watchbase.data.model.Show
import com.example.watchbase.ui.screens.designsystem.ActionButton
import com.example.watchbase.ui.screens.designsystem.Heading
import com.example.watchbase.ui.screens.designsystem.TopBar
import com.example.watchbase.ui.viewmodel.DetailViewModel
import com.example.watchbase.ui.viewmodel.HomeViewModel

@Composable
fun DetailScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToCastScreen: () -> Unit
) {
    homeViewModel.selectedShow.value?.let {
        LaunchedEffect(key1 = it) {
            detailViewModel.run {
                getShowCasts(showId = it.id, showType = homeViewModel.selectedShowType.value)
                getSimilarShows(showId = it.id, showType = homeViewModel.selectedShowType.value)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        homeViewModel.selectedShow.value?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Poster(posterPath = it.backdropPath)
                TopBar(
                    isNavigateUpAvailable = homeViewModel.selectedShows.size > 1,
                    onNavigateUp = { homeViewModel.navigateUp() },
                    onClickClose = {
                        homeViewModel.clearSelectedShows()
                        onNavigateToHomeScreen()
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Title(showTitle = it.title)
                Spacer(modifier = Modifier.height(2.dp))

                ShowDetails(selectedShow = it)
                Spacer(modifier = Modifier.height(8.dp))

                Casts(
                    castList = detailViewModel.cast.value,
                    onNavigateToCastScreen = onNavigateToCastScreen
                )
                Spacer(modifier = Modifier.height(8.dp))

                Overview(overview = it.overview)
                Spacer(modifier = Modifier.height(16.dp))

                ActionButtons(onClickAddToMyList = { }, onClickLikeShow = {})
                Spacer(modifier = Modifier.height(16.dp))

                SimilarMovies(
                    homeViewModel = homeViewModel,
                    showList = detailViewModel.similarShows.value.collectAsLazyPagingItems()
                )
            }
        }
    }
}

@Composable
fun Poster(posterPath: String?) {
    posterPath?.let {
        AsyncImage(
            model = "${BuildConfig.BASE_BACKDROP_IMAGE_URL}$it",
            contentScale = ContentScale.FillWidth,
            contentDescription = "Show",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun Title(showTitle: String) {
    Text(
        text = showTitle,
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun ShowDetails(selectedShow: Show) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "★ ${selectedShow.voteAverage}",
            fontSize = 14.sp,
            color = colorResource(id = R.color.text_color_secondary)
        )
        Text(
            text = " · ${selectedShow.releaseDate}", fontSize = 14.sp,
            color = colorResource(id = R.color.text_color_secondary)
        )
        selectedShow.runtime?.let {
            Text(
                text = " · $it", fontSize = 14.sp,
                color = colorResource(id = R.color.text_color_secondary)
            )
        }
    }
}

@Composable
fun ActionButtons(
    onClickAddToMyList: () -> Unit,
    onClickLikeShow: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
    ) {
        ActionButton(imageVector = Icons.Default.Add,
            label = "MY LIST",
            contentColor = Color.White,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                .clickable { onClickAddToMyList() })
        Spacer(modifier = Modifier.width(8.dp))
        ActionButton(imageVector = Icons.Default.FavoriteBorder,
            label = "LIKE",
            contentColor = Color.White,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                .clickable { onClickLikeShow() })
    }
}

@Composable
fun Overview(overview: String) {
    Text(text = overview, color = colorResource(id = R.color.text_color_secondary))
}

@Composable
fun Casts(
    castList: List<Cast>,
    onNavigateToCastScreen: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        Row(modifier = Modifier.weight(0.7f)) {
            Text(text = "Casts", fontWeight = FontWeight.Medium, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            var castNames = ""
            castList.forEachIndexed { index, cast ->
                castNames += when (index) {
                    castList.size - 1 -> cast.name
                    else -> "${cast.name}, "
                }
            }
            Text(
                text = castNames,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            modifier = Modifier
                .weight(0.3f)
                .clickable { onNavigateToCastScreen() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "View All", fontWeight = FontWeight.Medium, color = Color.White)
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                modifier = Modifier
                    .width(14.dp)
                    .height(14.dp),
                contentDescription = "View All",
                tint = Color.White
            )
        }
    }

}

@Composable
fun SimilarMovies(
    homeViewModel: HomeViewModel,
    showList: LazyPagingItems<Show>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Heading(title = "More Like This")
        Spacer(modifier = Modifier.height(12.dp))
        when (showList.loadState.refresh) {
            is LoadState.NotLoading -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(showList) { show ->
                        show?.let {
                            AsyncImage(
                                model = "${BuildConfig.BASE_POSTER_IMAGE_URL}${it.posterPath}",
                                contentScale = ContentScale.FillHeight,
                                contentDescription = "Show",
                                modifier = Modifier
                                    .height(200.dp)
                                    .clickable {
                                        homeViewModel.addSelectedShow(show)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}