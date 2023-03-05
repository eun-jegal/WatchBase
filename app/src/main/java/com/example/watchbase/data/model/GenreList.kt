package com.example.watchbase.data.model


import com.google.gson.annotations.SerializedName

data class GenreList(
    @SerializedName("genres")
    val genres: List<Genre>
)