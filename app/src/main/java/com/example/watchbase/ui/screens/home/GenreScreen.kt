package com.example.watchbase.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchbase.ui.viewmodel.HomeViewModel

@Composable
fun GenreScreen(
    homeViewModel: HomeViewModel,
    onNavigateToHomeScreen: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                tint = Color.White,
                contentDescription = "close",
                modifier = modifier.clickable { onNavigateToHomeScreen() })
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(items = homeViewModel.genres) { genre ->
                Text(
                    text = genre.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = modifier.clickable(enabled = true, onClick = {
                        homeViewModel.selectedGenre.value = genre
                        onNavigateToHomeScreen()
                    })
                )
            }
        }
    }

}