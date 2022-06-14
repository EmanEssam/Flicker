package com.test.photoapp.core.di

import com.test.flickr.domain.photos.GetPhotosUseCase
import com.test.flickr.domain.photos.GetPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providePhotosUseCase(
        photosUseCaseImpl: GetPhotosUseCaseImpl
    ): GetPhotosUseCase {
        return photosUseCaseImpl
    }

}