package br.com.movieapp.movie_detail_feature.presetation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presetation.components.common.MovieAppBar
import br.com.movieapp.movie_detail_feature.presetation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presetation.state.MovieDetailsState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    onAddFavorite: (Movie) -> Unit,
    uiState: MovieDetailsState,
) {

    val pagingMovieSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.detail_movie
            )
        },
        content = {
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMovieSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = { movie ->
                    onAddFavorite(movie)
                }
            )
        }
    )
}