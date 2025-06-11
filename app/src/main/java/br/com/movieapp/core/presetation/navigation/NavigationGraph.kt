package br.com.movieapp.core.presetation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.Constants
import br.com.movieapp.movie_detail_feature.presetation.MovieDetailScreen
import br.com.movieapp.movie_detail_feature.presetation.MovieDetailViewModel
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.search_movie_feature.presetation.MovieSearchViewModel
import br.com.movieapp.search_movie_feature.presetation.MovieSearchEvent
import br.com.movieapp.search_movie_feature.presetation.MovieSearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailsMovie = { id ->
                    navController.navigate(
                        DetailScreenNav.DetailsScreen.passMovieId(
                            movieId = id
                        )
                    )
                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = { id ->
                    navController.navigate(
                        DetailScreenNav.DetailsScreen.passMovieId(
                            movieId = id
                        )
                    )

                }
            )
        }
        composable(BottomNavItem.MovieFavorite.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState =
                viewModel.uiState.movies.collectAsStateWithLifecycle(initialValue = emptyList())

            MovieFavoriteScreen(
                movies = uiState.value,
                navigateToDetailMovie = { movieId ->
                    navController.navigate(DetailScreenNav.DetailsScreen.passMovieId(movieId = movieId))
                }
            )
        }

        composable(
            route = DetailScreenNav.DetailsScreen.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val favorite = viewModel::onAddFavorite
            MovieDetailScreen(
                uiState = uiState,
                onAddFavorite = favorite,
            )
        }
    }
}