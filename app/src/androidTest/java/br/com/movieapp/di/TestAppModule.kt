package br.com.movieapp.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.core.data.local.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context = context,
            klass = MovieDataBase::class.java
        ).allowMainThreadQueries().build()

}