package com.example.watchbase.ui.screens.mylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchbase.R
import com.example.watchbase.ui.screens.designsystem.Heading

@Composable
fun MyListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        TopBar()
        LikedShowAndWatchLaterList()
        MyList()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            tint = Color.White,
            contentDescription = "Search",
            modifier = Modifier.clickable { })
    }
}

@Composable
fun LikedShowAndWatchLaterList() {
    DefaultList(
        leadingIcon = painterResource(id = R.drawable.ic_baseline_favorite_24),
        contentDescription = "Liked Shows",
        onClick = {})
    DefaultList(
        leadingIcon = painterResource(id = R.drawable.ic_baseline_history_24),
        contentDescription = "Watch Later",
        onClick = {})
}

@Composable
fun DefaultList(
    leadingIcon: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
            ) {
                Icon(
                    painter = leadingIcon,
                    contentDescription = contentDescription,
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = contentDescription,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            tint = Color.White,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun MyList() {
    Column(modifier = Modifier.fillMaxSize()) {
        Heading(title = "My List")
    }
}