package com.feature.home.data.di;

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
public final class HomeModule_ProvideRetrofitBuilderFactory implements Factory<Retrofit.Builder> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private HomeModule_ProvideRetrofitBuilderFactory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public Retrofit.Builder get() {
    return provideRetrofitBuilder(okHttpClientProvider.get());
  }

  public static HomeModule_ProvideRetrofitBuilderFactory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new HomeModule_ProvideRetrofitBuilderFactory(okHttpClientProvider);
  }

  public static Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(HomeModule.INSTANCE.provideRetrofitBuilder(okHttpClient));
  }
}
