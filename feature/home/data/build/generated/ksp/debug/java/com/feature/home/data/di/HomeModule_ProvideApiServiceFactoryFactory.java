package com.feature.home.data.di;

import com.feature.home.data.source.remote.api.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class HomeModule_ProvideApiServiceFactoryFactory implements Factory<ApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Retrofit.Builder> retrofitBuilderProvider;

  private HomeModule_ProvideApiServiceFactoryFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Retrofit.Builder> retrofitBuilderProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.retrofitBuilderProvider = retrofitBuilderProvider;
  }

  @Override
  public ApiService get() {
    return provideApiServiceFactory(okHttpClientProvider.get(), retrofitBuilderProvider.get());
  }

  public static HomeModule_ProvideApiServiceFactoryFactory create(
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<Retrofit.Builder> retrofitBuilderProvider) {
    return new HomeModule_ProvideApiServiceFactoryFactory(okHttpClientProvider, retrofitBuilderProvider);
  }

  public static ApiService provideApiServiceFactory(OkHttpClient okHttpClient,
      Retrofit.Builder retrofitBuilder) {
    return Preconditions.checkNotNullFromProvides(HomeModule.INSTANCE.provideApiServiceFactory(okHttpClient, retrofitBuilder));
  }
}
