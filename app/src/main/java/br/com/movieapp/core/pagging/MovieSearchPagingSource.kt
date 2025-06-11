package br.com.movieapp.core.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : PagingSource<Int, MovieSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val achorPage = state.closestPageToPosition(anchorPosition)
            achorPage?.prevKey?.plus(LIMIT) ?: achorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {

            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchMovies(page = pageNumber, query = query)
            val totalPage = response.totalPages
            val movies = response.movies
            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (pageNumber == totalPage) null else pageNumber + 1
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}