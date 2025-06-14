package br.com.movieapp.movie_popular_feature.data.repository

import androidx.paging.PagingSource
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.pagging.MoviePagingSource
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {

    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource)
    }
}