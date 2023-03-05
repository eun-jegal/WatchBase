package com.example.watchbase.data.model.tvshow


import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvShows: List<TvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class TrendingTvShowList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingTvShows: List<TrendingTvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)