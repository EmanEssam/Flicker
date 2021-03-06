package com.test.flickr.di

import android.content.Context
import com.test.flickr.heloper.RetrofitFactory
import com.test.flickr.data.remote.PhotosApiInterface
import com.test.photoapp.core.data.remote.photos.PhotosRemoteDataSource
import com.test.flickr.data.remote.PhotosRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providePhotosApi(
        remoteDataSource: RetrofitFactory,
        @ApplicationContext context: Context
    ): PhotosApiInterface {
        return remoteDataSource.getService()
    }

    @Provides
    fun providePhotosRemoteDataSourceImpl(
        photosRemoteDataSourceImpl: PhotosRemoteDataSourceImpl
    ): PhotosRemoteDataSource {
        return photosRemoteDataSourceImpl
    }
}