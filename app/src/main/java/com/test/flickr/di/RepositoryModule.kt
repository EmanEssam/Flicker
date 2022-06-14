package com.test.flickr.di

import com.test.flickr.data.repository.PhotosRepository
import com.test.flickr.data.repository.PhotosRepositoryImpl
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
    fun bindPhotosRepository(photosRepository: PhotosRepositoryImpl): PhotosRepository =
        photosRepository
}