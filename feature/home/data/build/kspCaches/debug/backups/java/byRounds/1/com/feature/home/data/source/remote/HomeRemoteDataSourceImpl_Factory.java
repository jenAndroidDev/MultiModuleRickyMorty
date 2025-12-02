package com.feature.home.data.source.remote;

import com.feature.home.data.source.remote.api.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import jjcodes.network.utis.NetworkHelper;

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
public final class HomeRemoteDataSourceImpl_Factory implements Factory<HomeRemoteDataSourceImpl> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<NetworkHelper> networkHelperProvider;

  private HomeRemoteDataSourceImpl_Factory(Provider<ApiService> apiServiceProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.networkHelperProvider = networkHelperProvider;
  }

  @Override
  public HomeRemoteDataSourceImpl get() {
    return newInstance(apiServiceProvider.get(), networkHelperProvider.get());
  }

  public static HomeRemoteDataSourceImpl_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    return new HomeRemoteDataSourceImpl_Factory(apiServiceProvider, networkHelperProvider);
  }

  public static HomeRemoteDataSourceImpl newInstance(ApiService apiService,
      NetworkHelper networkHelper) {
    return new HomeRemoteDataSourceImpl(apiService, networkHelper);
  }
}
