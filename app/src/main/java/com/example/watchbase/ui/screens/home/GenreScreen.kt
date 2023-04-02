package com.example.watchbase.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchbase.ui.screens.designsystem.BackButton
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
    ) {
        BackButton(onNavigateUp = { onNavigateToHomeScreen() }, modifier = modifier)
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
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