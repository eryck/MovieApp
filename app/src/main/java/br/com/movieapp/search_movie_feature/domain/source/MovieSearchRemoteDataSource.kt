package br.com.movieapp.search_movie_feature.domain.source

import br.com.movieapp.core.data.remote.response.MovieSearchPaging
import br.com.movieapp.core.pagging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging
}