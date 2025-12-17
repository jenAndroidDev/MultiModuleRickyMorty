package com.rmworld.feature.detail.data.di


import com.rmworld.feature.detail.data.repository.DetailRepositoryImpl
import com.rmworld.feature.detail.data.source.remote.DetailRemoteDataSource
import com.rmworld.feature.detail.data.source.remote.DetailRemoteDataSourceImpl
import com.rmworld.feature.detail.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailBinderModule {

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository

    @Binds
    @Singleton
    abstract fun bindDetailRemoteDataSource(
        detailRemoteDataSourceImpl: DetailRemoteDataSourceImpl
    ): DetailRemoteDataSource
}
