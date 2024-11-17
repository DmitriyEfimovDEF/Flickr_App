package com.dimadef.flickr.data.search.di

import com.dimadef.flickr.data.search.remote.service.FlickrService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal object ServiceModule {

    @Provides
    @Reusable
    fun providePhotoService(retrofit: Retrofit): FlickrService = retrofit.create(FlickrService::class.java)
}
