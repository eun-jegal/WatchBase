package com.example.watchbase.data.model


import com.google.gson.annotations.SerializedName

data class CastList(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
)