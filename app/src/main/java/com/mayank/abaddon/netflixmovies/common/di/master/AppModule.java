package com.mayank.abaddon.netflixmovies.common.di.master;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.MovieApplication;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Mayank.
 */

@Module class AppModule {

  private MovieApplication movieApplication;

  AppModule(MovieApplication application) {
    movieApplication = application;
  }

  @Provides @Singleton Context providesContext() {
    return movieApplication;
  }

  @Provides @Singleton SharedPreferences providesSharedPrefs() {
    return PreferenceManager.getDefaultSharedPreferences(movieApplication);
  }
}
