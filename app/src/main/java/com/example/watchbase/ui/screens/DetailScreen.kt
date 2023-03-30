package com.example.watchbase.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.watchbase.data.model.Show
import com.example.watchbase.ui.screens.designsystem.BackButton
import com.example.watchbase.ui.screens.home.BASE_POSTER_IMAGE_URL

@Composable
fun DetailScreen(
    currentShow: Show,
    onNavigateToHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BackButton(onNavigateUp = { onNavigateToHomeScreen() })
        Poster(currentShow = currentShow)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Title(currentShow = currentShow)
            ShowDetails(currentShow = currentShow)
            AddToMyListButton(currentShow = currentShow)
            Plot(currentShow = currentShow)
            Casts(currentShow = currentShow)
            SimilarMovies(currentShow = currentShow)
        }
    }
}

@Composable
fun Poster(currentShow: Show) {
    val imagePath = "$BASE_POSTER_IMAGE_URL${currentShow.posterPath}"
    AsyncImage(
        model = imagePath,
        contentDescription = "Show",
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    )
}

@Composable
fun Title(currentShow: Show) {
    Text(
        text = currentShow.title,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ShowDetails(currentShow: Show) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = currentShow.voteAverage.toString(), fontSize = 14.sp)
        Text(text = "|", fontSize = 14.sp)
        Text(text = currentShow.releaseDate, fontSize = 14.sp)
        Text(text = "|", fontSize = 14.sp)
        Text(text = currentShow.runtime.toString(), fontSize = 14.sp)
        //TODO: Genres
    }
}

@Composable
fun AddToMyListButton(currentShow: Show) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clickable { },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
        Text(text = "Add to My List", color = Color.White, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun Plot(currentShow: Show) {
    Text(text = currentShow.overview)
}

@Composable
fun Casts(currentShow: Show) {
    //ToDo : Casts list
}

@Composable
fun SimilarMovies(currentShow: Show) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Similar Movies")
        //ToDo : Movies list
    }
}