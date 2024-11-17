package com.dimadef.flickr.data.search.di

import com.dimadef.flickr.data.search.repository.DefaultPhotosRepository
import com.dimadef.flickr.domain.search.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    @Reusable
    fun bindSearchRepository(repo: DefaultPhotosRepository): PhotosRepository
}
