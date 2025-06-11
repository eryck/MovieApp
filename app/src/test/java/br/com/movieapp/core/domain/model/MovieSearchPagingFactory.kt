package br.com.movieapp.core.domain.model

import br.com.movieapp.core.data.remote.response.MovieSearchPaging

class MovieSearchPagingFactory {
    fun create() = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url"
            ),
            MovieSearch(
                id = 2,
                voteAverage = 7.5,
                imageUrl = "Url"
            )
        )
    )
}