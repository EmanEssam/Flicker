package com.test.photoapp.core.di

import com.test.flickr.domain.photos.GetMoviesPhotosUseCase
import com.test.flickr.domain.photos.GetMoviesPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideMoviesUseCase(
        moviesPhotosUseCaseImpl: GetMoviesPhotosUseCaseImpl
    ): GetMoviesPhotosUseCase {
        return moviesPhotosUseCaseImpl
    }

}