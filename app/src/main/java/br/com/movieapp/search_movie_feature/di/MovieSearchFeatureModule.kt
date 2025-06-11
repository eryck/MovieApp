package br.com.movieapp.search_movie_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.search_movie_feature.data.repository.MovieSearchRepositoryImp
import br.com.movieapp.search_movie_feature.data.source.MovieSearchRemoteDataSourceImp
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchFeatureModule {

    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImp(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImp(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase {
        return GetMovieSearchUseCaseImpl(repository = repository)
    }
}