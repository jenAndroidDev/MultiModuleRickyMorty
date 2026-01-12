package com.rmworld.feature.detail.data.source.remote.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.rmworld.core.network.ApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule{




    @Provides
    @Singleton
    fun provideApiServiceFactory(
        okHttpClient: OkHttpClient,
        retrofitBuilder: Retrofit.Builder): DetailApiService {

       return ApiServiceFactory(okHttpClient, retrofitBuilder).create(
            serviceType = DetailApiService::class.java,
            baseUrl = "https://rickandmortyapi.com/api/",
            shouldAddSignInterceptor = false,
        )
    }

}