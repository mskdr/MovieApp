package com.muhammetkudur.domain.repository

import androidx.paging.PagingData
import com.muhammetkudur.domain.model.TopRatedMovieEntity
import kotlinx.coroutines.flow.Flow

/**
 *    Created By Muhammet KÜDÜR
 *    17.07.2023
 */

interface MovieRepository {
    fun fetchTopRatedMovies(): Flow<PagingData<TopRatedMovieEntity>>
}