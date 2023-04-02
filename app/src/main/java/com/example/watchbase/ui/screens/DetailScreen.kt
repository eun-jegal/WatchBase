package com.example.watchbase.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.ui.screens.designsystem.ActionButton
import com.example.watchbase.ui.screens.designsystem.BackButton
import com.example.watchbase.ui.viewmodel.DetailViewModel
import com.example.watchbase.ui.viewmodel.HomeViewModel

@Composable
fun DetailScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    onClickAddToMyList: () -> Unit,
    onNavigateToHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        homeViewModel.selectedShow.value?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.32f),
                contentAlignment = Alignment.TopStart
            ) {
                Poster(posterPath = it.backdropPath)
                BackButton(onNavigateUp = { onNavigateToHomeScreen() })
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Title(showTitle = it.title)

                ShowDetails(selectedShow = it)
                Spacer(modifier = Modifier.height(8.dp))

                ActionButtons(onClickAddToMyList = onClickAddToMyList, onClickLikeShow = {})
                Spacer(modifier = Modifier.height(8.dp))

                Overview(overview = it.overview)
                Spacer(modifier = Modifier.height(16.dp))

//            Casts(homeViewModel = homeViewModel)
//            SimilarMovies(homeViewModel = homeViewModel)
            }
        }
    }
}

@Composable
fun Poster(posterPath: String?) {
    posterPath?.let {
        AsyncImage(
            model = "${BuildConfig.BASE_BACKDROP_IMAGE_URL}$it",
            contentDescription = "Show",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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
        ActionButton(imageVector = Icons.Default.Add, label = "MY LIST", modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
            .clickable { onClickAddToMyList() })
        Spacer(modifier = Modifier.width(8.dp))
        ActionButton(imageVector = Icons.Default.FavoriteBorder, label = "LIKE", modifier = Modifier
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
    homeViewModel: HomeViewModel
) {
    //ToDo : Casts list
}

@Composable
fun SimilarMovies(
    homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Similar Movies")
        //ToDo : Movies list
    }
}