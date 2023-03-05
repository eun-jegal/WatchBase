package com.example.watchbase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchbase.R
import com.example.watchbase.ui.screens.designsystem.Chip
import com.example.watchbase.ui.screens.designsystem.Heading

@Composable
fun Home(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TVShowOrMovieChips(modifier)
        GenreList(modifier)
        HeadingAndCarousel(
            title = stringResource(id = R.string.title_top_rated),
            modifier = modifier
        )
        HeadingAndCarousel(
            title = stringResource(id = R.string.title_popular),
            modifier = modifier
        )
    }
}

@Composable
fun TVShowOrMovieChips(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Chip(selected = true, text = stringResource(id = R.string.category_tv_show))
        Chip(selected = false, text = stringResource(id = R.string.category_movies))
    }
}

@Composable
fun GenreList(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.genres),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        val genreList =
            listOf(
                "All",
                "Action",
                "Comedy",
                "Fantasy",
                "Romance",
                "Horror",
                "Thriller",
                "All",
                "Action",
                "Comedy",
                "Fantasy",
                "Romance",
                "Horror",
                "Thriller"
            )
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(genreList) { index, genre ->
                if (index == 0) {
                    Chip(selected = true, text = genre)
                } else {
                    Chip(selected = false, text = genre)
                }
            }
        }
    }
}

@Composable
fun HeadingAndCarousel(
    title: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Heading(title = title)
        Spacer(modifier = modifier.height(12.dp))
        LazyRow {

        }
    }
}