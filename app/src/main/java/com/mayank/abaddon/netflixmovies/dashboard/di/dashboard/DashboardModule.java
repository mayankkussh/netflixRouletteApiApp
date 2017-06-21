package com.mayank.abaddon.netflixmovies.dashboard.di.dashboard;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by Mayank
 */

@Module public class DashboardModule {
  private DashboardActivity dashboardActivity;

  public DashboardModule(DashboardActivity activity) {
    dashboardActivity = activity;
  }

  @Provides @DashboardScope @Named("componentName") public ComponentName getComponentName() {
    return dashboardActivity.getComponentName();
  }

  @Provides @DashboardScope @Named("dashboardFragmentManager")
  public FragmentManager getFragmentManager() {
    return dashboardActivity.getSupportFragmentManager();
  }

  @Provides @DashboardScope SearchManager providesSearchManager() {
    return (SearchManager) dashboardActivity.getSystemService(Context.SEARCH_SERVICE);
  }
}
