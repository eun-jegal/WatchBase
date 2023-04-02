package com.example.watchbase.data.model


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String
)