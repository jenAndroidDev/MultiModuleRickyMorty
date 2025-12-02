package com.feature.home.data.repository;

import com.feature.home.data.source.remote.HomeRemoteDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class HomeRepositoryImpl_Factory implements Factory<HomeRepositoryImpl> {
  private final Provider<HomeRemoteDataSource> remoteDataSourceProvider;

  private HomeRepositoryImpl_Factory(Provider<HomeRemoteDataSource> remoteDataSourceProvider) {
    this.remoteDataSourceProvider = remoteDataSourceProvider;
  }

  @Override
  public HomeRepositoryImpl get() {
    return newInstance(remoteDataSourceProvider.get());
  }

  public static HomeRepositoryImpl_Factory create(
      Provider<HomeRemoteDataSource> remoteDataSourceProvider) {
    return new HomeRepositoryImpl_Factory(remoteDataSourceProvider);
  }

  public static HomeRepositoryImpl newInstance(HomeRemoteDataSource remoteDataSource) {
    return new HomeRepositoryImpl(remoteDataSource);
  }
}
