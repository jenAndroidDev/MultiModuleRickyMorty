package com.feature.home.presentation;

import com.feature.home.domain.usecase.GetAllCharactersUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetAllCharactersUseCase> useCaseProvider;

  private HomeViewModel_Factory(Provider<GetAllCharactersUseCase> useCaseProvider) {
    this.useCaseProvider = useCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(useCaseProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<GetAllCharactersUseCase> useCaseProvider) {
    return new HomeViewModel_Factory(useCaseProvider);
  }

  public static HomeViewModel newInstance(GetAllCharactersUseCase useCase) {
    return new HomeViewModel(useCase);
  }
}
