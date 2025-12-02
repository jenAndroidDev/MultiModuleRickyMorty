package com.feature.home.data.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import jjcodes.network.utis.NetworkHelper;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class HomeModule_ProvideNetworkHelperFactory implements Factory<NetworkHelper> {
  private final Provider<Context> contextProvider;

  private HomeModule_ProvideNetworkHelperFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NetworkHelper get() {
    return provideNetworkHelper(contextProvider.get());
  }

  public static HomeModule_ProvideNetworkHelperFactory create(Provider<Context> contextProvider) {
    return new HomeModule_ProvideNetworkHelperFactory(contextProvider);
  }

  public static NetworkHelper provideNetworkHelper(Context context) {
    return Preconditions.checkNotNullFromProvides(HomeModule.INSTANCE.provideNetworkHelper(context));
  }
}
