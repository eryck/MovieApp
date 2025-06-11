package br.com.movieapp.movie_detail_feature.presetation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.Constants
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.presetation.state.MovieDetailsState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailsState())
        private set

    private val movieId = savedStateHandle.get<Int>(key = Constants.MOVIE_DETAILS_ARGUMENT_KEY)

    init {
        movieId?.let { safeMovieId ->
            checkedFavorite(MovieDetailsEvent.CheckedFavorite(safeMovieId))
            getMovieDetail(MovieDetailsEvent.GetMovieDetail(safeMovieId))
        }
    }

    private fun checkedFavorite(checkedFavorite: MovieDetailsEvent.CheckedFavorite) {
        event(checkedFavorite)
    }

    private fun getMovieDetail(getMovieDetail: MovieDetailsEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun onAddFavorite(movie: Movie) {
        if (uiState.iconColor == Color.White) {
            event(MovieDetailsEvent.AddFavorite(movie = movie))
        } else {
            event(MovieDetailsEvent.RemoveFavorite(movie = movie))
        }
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(
                        params = AddMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro ao cadastrar filme")
                            }

                            ResultData.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailsEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        params = IsMovieFavoriteUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = if (result.data == true) {
                                    uiState.copy(iconColor = Color.Red)
                                } else {
                                    uiState.copy(iconColor = Color.White)
                                }
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Um erro ocorreu")
                            }

                            ResultData.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailsEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                                UtilFunctions.logError("DETAIL", "Sucesso")
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro ao remover")
                            }

                            ResultData.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailsEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    val resultData = getMovieDetailUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId,
                            pagingConfig = pagingConfig()
                        )
                    )
                    when (resultData) {
                        is ResultData.Success -> {
                            uiState = uiState.copy(
                                isLoading = false,
                                movieDetails = resultData.data?.second,
                                results = resultData.data?.first ?: emptyFlow()
                            )
                        }

                        is ResultData.Failure -> {
                            uiState = uiState.copy(
                                isLoading = false,
                                error = resultData.e?.message.toString()
                            )
                            UtilFunctions.logError(
                                tag = "DETAIL_ERROR",
                                message = resultData.e?.message.toString()
                            )
                        }

                        is ResultData.Loading -> {
                            uiState = uiState.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun pagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        )
    }
}