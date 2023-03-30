package com.example.watchbase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.watchbase.R
import com.example.watchbase.data.model.Show
import com.example.watchbase.ui.navigation.ShowNavType
import com.example.watchbase.ui.screens.Screen
import com.example.watchbase.ui.screens.home.GenreScreen
import com.example.watchbase.ui.screens.home.HomeScreen
import com.example.watchbase.ui.screens.mylist.MyListScreen
import com.example.watchbase.ui.screens.search.SearchScreen
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
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.app_background)),
        builder = {
            composable(Screen.Home.route) {
                HomeScreen(
                    homeViewModel = homeViewModel,
                    onNavigateToGenreScreen = { navController.navigate(Screen.Genres.route) },
                    onNavigateToDetailScreen = { showId, showType ->
                        navController.navigate("${Screen.Detail.route}/$showId/$showType")
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
                SearchScreen()
            }
            composable(Screen.MyList.route) {
                MyListScreen()
            }
            composable(route = "${Screen.Detail.route}/{selected_show_id}/{show_type}",
                arguments = listOf(
                    navArgument("selected_show_id") {
                        type = NavType.StringType
                    },
                    navArgument("show_type") {
                        type = NavType.StringType
                    }
                )) {

            }
        })
}