package br.com.movieapp.movie_popular_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MoviePaging
import br.com.movieapp.core.pagging.MoviePagingSource
import br.com.movieapp.movie_popular_feature.data.mapper.toMovie
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MoviePaging {
        val response = service.getPopularMovies(page = page)
        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovie() }
        )
    }
}