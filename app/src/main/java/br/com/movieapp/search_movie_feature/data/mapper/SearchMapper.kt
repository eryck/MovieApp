package br.com.movieapp.search_movie_feature.data.mapper


import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.util.toPostUrl

fun MovieResult.toMovieSearch(): MovieSearch {
    return MovieSearch(
        id = id,
        voteAverage = voteAverage,
        imageUrl = posterPath.toPostUrl()
    )
}

fun List<MovieResult>.toMovieSearch() = map { searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toPostUrl(),
        voteAverage = searchResult.voteAverage
    )
}