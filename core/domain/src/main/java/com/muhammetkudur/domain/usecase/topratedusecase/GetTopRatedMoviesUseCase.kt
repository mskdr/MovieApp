package com.muhammetkudur.domain.usecase.topratedusecase

import androidx.paging.PagingData
import com.muhammetkudur.domain.model.TopRatedMovieEntity
import kotlinx.coroutines.flow.Flow

/**
 *    Created By Muhammet KÜDÜR
 *    17.07.2023
 */

interface GetTopRatedMoviesUseCase {
    suspend operator fun invoke(): Flow<PagingData<TopRatedMovieEntity>>
}