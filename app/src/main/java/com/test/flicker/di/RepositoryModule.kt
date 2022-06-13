package com.test.flicker.di

import com.test.flicker.data.repository.PhotosRepository
import com.test.flicker.data.repository.PhotosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun bindMoviesRepository(moviesRepository: PhotosRepositoryImpl): PhotosRepository =
        moviesRepository
}