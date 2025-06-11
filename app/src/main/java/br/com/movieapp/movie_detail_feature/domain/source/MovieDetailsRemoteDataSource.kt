package br.com.movieapp.movie_detail_feature.domain.source

import br.com.movieapp.core.data.remote.response.MoviePaging
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.pagging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging
    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}