package br.com.movieapp.search_movie_feature.presetation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presetation.components.common.MovieAppBar
import br.com.movieapp.search_movie_feature.presetation.components.SearchContent
import br.com.movieapp.search_movie_feature.presetation.state.MovieSearchState

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.search_movies
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onEvent(it)
                },
                onDetail = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )
}