package com.example.watchbase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.watchbase.R
import com.example.watchbase.ui.screens.CastScreen
import com.example.watchbase.ui.screens.DetailScreen
import com.example.watchbase.ui.screens.Screen
import com.example.watchbase.ui.screens.home.GenreScreen
import com.example.watchbase.ui.screens.home.HomeScreen
import com.example.watchbase.ui.screens.mylist.MyListScreen
import com.example.watchbase.ui.screens.search.SearchScreen
import com.example.watchbase.ui.viewmodel.DetailViewModel
import com.example.watchbase.ui.viewmodel.HomeViewModel
import com.example.watchbase.ui.viewmodel.SearchViewModel

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    searchViewModel: SearchViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.app_background)),
        builder = {
            composable(Screen.Home.route) {
                HomeScreen(
                    homeViewModel = homeViewModel,
                    onNavigateToGenreScreen = { navController.navigate(Screen.Genres.route) },
                    onNavigateToDetailScreen = {
                        homeViewModel.addSelectedShow(it)
                        navController.navigate(Screen.Detail.route)
                    }
                )
            }
            composable(Screen.Genres.route) {
                GenreScreen(
                    homeViewModel = homeViewModel,
                    onNavigateToHomeScreen = { navController.navigate(Screen.Home.route) },
                    modifier = modifier
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    searchViewModel = searchViewModel,
                    onNavigateToDetailScreen = {
                        homeViewModel.addSelectedShow(it)
                        navController.navigate(Screen.Detail.route)
                    })
            }
            composable(Screen.MyList.route) {
                MyListScreen()
            }
            composable(Screen.Detail.route) {
                DetailScreen(
                    homeViewModel = homeViewModel,
                    detailViewModel = detailViewModel,
                    onNavigateToHomeScreen = { navController.navigate(Screen.Home.route) },
                    onNavigateToCastScreen = { navController.navigate(Screen.Casts.route) }
                )
            }
            composable(Screen.Casts.route) {
                CastScreen(
                    detailViewModel = detailViewModel,
                    onNavigateToDetailScreen = { navController.popBackStack() }
                )
            }
        })
}