package br.com.movieapp.core.domain.model

import br.com.movieapp.core.data.remote.response.MoviePaging

class MoviePagingFactory {
    fun create() = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                imageUrl = "Url"
            ),
            Movie(
                id = 2,
                title = "JohnWick",
                voteAverage = 7.5,
                imageUrl = "Url"
            )
        )
    )
}