package com.example.watchbase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.watchbase.ui.AppNavHost
import com.example.watchbase.ui.BottomNavBar
import com.example.watchbase.ui.viewmodel.DetailViewModel
import com.example.watchbase.ui.viewmodel.HomeViewModel
import com.example.watchbase.ui.viewmodel.SearchViewModel
import com.example.watchbase.ui.viewmodel.factory.DetailViewModelFactory
import com.example.watchbase.ui.viewmodel.factory.HomeViewModelFactory
import com.example.watchbase.ui.viewmodel.factory.SearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    @Inject
    lateinit var detailViewModelFactory: DetailViewModelFactory

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var searchViewModel: SearchViewModel

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        detailViewModel =
            ViewModelProvider(this, detailViewModelFactory)[DetailViewModel::class.java]
        searchViewModel =
            ViewModelProvider(this, searchViewModelFactory)[SearchViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavBar(navController = navController) }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(
                        homeViewModel = homeViewModel,
                        detailViewModel = detailViewModel,
                        searchViewModel = searchViewModel,
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }

        }
    }
}