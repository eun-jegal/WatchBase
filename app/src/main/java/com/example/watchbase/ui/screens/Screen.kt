package com.example.watchbase.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.watchbase.R

sealed class Screen(val route: String, @DrawableRes val iconId: Int, @StringRes val stringId: Int) {
    object Home : Screen("home", R.drawable.ic_baseline_home_24, R.string.home)
    object Search : Screen("search", R.drawable.ic_baseline_search_24, R.string.search)
    object MyList : Screen("myList", R.drawable.ic_baseline_video_library_24, R.string.my_list)
    object Genres : Screen("genres", -1, R.string.genres)
    object Detail : Screen("detail", -1, R.string.detail)
    object Casts: Screen("casts", -1, R.string.casts)
}
