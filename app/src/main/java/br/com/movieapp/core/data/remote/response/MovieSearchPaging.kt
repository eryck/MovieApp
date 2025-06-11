package br.com.movieapp.core.data.remote.response

import br.com.movieapp.core.domain.model.MovieSearch

data class MovieSearchPaging(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<MovieSearch>
)