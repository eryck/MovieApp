package br.com.movieapp.core.presetation.navigation

import br.com.movieapp.core.util.Constants.MOVIE_DETAILS_ARGUMENT_KEY

sealed class DetailScreenNav(val route: String) {
    object DetailsScreen : DetailScreenNav(
        route = "movie_detail_destination?$MOVIE_DETAILS_ARGUMENT_KEY=" + "{$MOVIE_DETAILS_ARGUMENT_KEY}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?$MOVIE_DETAILS_ARGUMENT_KEY=$movieId"
    }
}