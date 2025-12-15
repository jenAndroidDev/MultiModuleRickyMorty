package com.rmworld.feature.home.data.di

import android.annotation.SuppressLint
import com.rmworld.feature.home.data.repository.HomeRepositoryImpl
import com.rmworld.feature.home.data.source.remote.HomeRemoteDataSource
import com.rmworld.feature.home.data.source.remote.HomeRemoteDataSourceImpl
import com.rmworld.feature.home.data.source.remote.api.ApiService
import com.rmworld.feature.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jjcodes.network.ApiServiceFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeBinderModule {

    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataSource(
        homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl
    ): HomeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

}

@Module
@InstallIn(SingletonComponent::class)
object HomeModule{

    @SuppressLint("NewApi")
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiServiceFactory(
        okHttpClient: OkHttpClient,
        retrofitBuilder: Retrofit.Builder): ApiService {

       return ApiServiceFactory(okHttpClient, retrofitBuilder).create(
            serviceType = ApiService::class.java,
            baseUrl = "https://rickandmortyapi.com/api/",
            shouldAddSignInterceptor = false,
        )
    }

}