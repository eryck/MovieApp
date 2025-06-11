package br.com.movieapp.movie_popular_feature.domain.source

import br.com.movieapp.core.data.remote.response.MoviePaging
import br.com.movieapp.core.pagging.MoviePagingSource

interface MoviePopularRemoteDataSource {
    fun getPopularMoviesPagingSource(): MoviePagingSource

    suspend fun getPopularMovies(page: Int): MoviePaging
}