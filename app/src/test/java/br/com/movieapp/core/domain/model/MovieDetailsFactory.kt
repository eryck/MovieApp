package br.com.movieapp.core.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

class MovieDetailsFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> {
            MovieDetails(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                genres = listOf("Ação", "Aventura"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "Url",
                releaseDate = "01/01/2020",
                duration = 150
            )
        }

        Poster.AvengersTwo -> {
            MovieDetails(
                id = 1,
                title = "Avengers 2",
                voteAverage = 7.1,
                genres = listOf("Ação", "Aventura"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "Url",
                releaseDate = "01/01/2022",
                duration = 170
            )
        }
    }

    sealed class Poster {
        object Avengers : Poster()
        object AvengersTwo : Poster()
    }
}