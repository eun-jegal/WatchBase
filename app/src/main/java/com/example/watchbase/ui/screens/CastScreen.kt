package com.example.watchbase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.watchbase.BuildConfig
import com.example.watchbase.R
import com.example.watchbase.data.model.Cast
import com.example.watchbase.ui.viewmodel.DetailViewModel

@Composable
fun CastScreen(
    detailViewModel: DetailViewModel,
    onNavigateToDetailScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Casts", color = Color.White) },
                backgroundColor = colorResource(id = R.color.app_background),
                navigationIcon = {
                    IconButton(onClick = { onNavigateToDetailScreen() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                })
        },
        backgroundColor = Color.Transparent,
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)) {
                items(detailViewModel.cast.value) { cast: Cast ->
                    Cast(cast = cast)
                }
            }
        }
    )
}

@Composable
fun Cast(
    cast: Cast
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "${BuildConfig.BASE_POSTER_IMAGE_URL}${cast.profilePath}",
            contentScale = ContentScale.Crop,
            contentDescription = cast.name,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = cast.name,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}