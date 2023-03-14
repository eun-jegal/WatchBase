package com.example.watchbase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.watchbase.R
import com.example.watchbase.ui.screens.Home
import com.example.watchbase.ui.screens.MyList
import com.example.watchbase.ui.screens.Screen
import com.example.watchbase.ui.screens.Search
import com.example.watchbase.ui.viewmodel.HomeViewModel

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.app_background)),
        builder = {
            composable(Screen.Home.route) {
                Home(homeViewModel = homeViewModel, modifier = modifier)
            }
            composable(Screen.Search.route) {
                Search()
            }
            composable(Screen.MyList.route) {
                MyList()
            }
        })
}