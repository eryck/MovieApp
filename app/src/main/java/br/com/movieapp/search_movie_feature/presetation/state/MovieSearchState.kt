package br.com.movieapp.search_movie_feature.presetation.state

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState(
    val query: String = "",
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow()
)
