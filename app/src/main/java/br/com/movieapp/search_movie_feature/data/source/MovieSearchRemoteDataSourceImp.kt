package br.com.movieapp.search_movie_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MovieSearchPaging
import br.com.movieapp.core.pagging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature.data.mapper.toMovieSearch
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImp @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(
            query = query,
            remoteDataSource = this
        )
    }

    override suspend fun getSearchMovies(
        page: Int,
        query: String
    ): MovieSearchPaging {
        val response = service.searchMovie(page = page, query = query)
        return MovieSearchPaging(
            page = response.page,
            totalResults = response.totalResults,
            totalPages = response.totalPages,
            movies = response.results.map { it.toMovieSearch() }
        )
    }
}