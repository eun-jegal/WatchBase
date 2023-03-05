package com.example.watchbase.data.repository

import com.example.watchbase.data.Resource
import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.model.ShowType

interface GenreRepository {
    suspend fun getGenres(showType: ShowType): Resource<GenreList>
}