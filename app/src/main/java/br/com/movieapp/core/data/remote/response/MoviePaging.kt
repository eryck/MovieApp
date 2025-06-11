package br.com.movieapp.core.data.remote.response

import br.com.movieapp.core.domain.model.Movie

data class MoviePaging(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<Movie>
)