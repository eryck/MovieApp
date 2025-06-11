package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DeleteMovieFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val deleteMovieFavoriteUseCase by lazy {
        DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `should return Success from ResulStatus when repository return success equal to unit`() =
        runTest {
            //Given
            whenever(movieFavoriteRepository.delete(movie)).thenReturn(Unit)
            //When
            val result = deleteMovieFavoriteUseCase.invoke(
                params = DeleteMovieFavoriteUseCase.Params(
                    movie = movie
                )
            ).first()
            //Then
            assertThat(result).isEqualTo(ResultData.Success(Unit))
        }

    @Test
    fun `must return Failure from ResultStatus when the repository throws an exception`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieFavoriteRepository.delete(movie)).thenThrow(exception)
            //When
            val result = deleteMovieFavoriteUseCase.invoke(
                params = DeleteMovieFavoriteUseCase.Params(movie = movie)
            ).first()

            //Then
            assertThat(result).isEqualTo(ResultData.Failure(exception))

        }

}