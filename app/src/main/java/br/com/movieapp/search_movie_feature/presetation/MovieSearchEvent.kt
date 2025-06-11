package br.com.movieapp.search_movie_feature.presetation

sealed class MovieSearchEvent {
    data class EnterQuery(val value: String) : MovieSearchEvent()
}