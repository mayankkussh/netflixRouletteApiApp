package com.mayank.abaddon.netflixmovies.dashboard.activity;

import android.os.Bundle;
import android.view.View;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.dashboard.events.MovieCardClickedEvent;
import de.greenrobot.event.EventBus;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */
public class DashboardActivity extends BaseActivity {

  @Inject DashboardNavigator dashboardNavigator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);
    setupUI(R.layout.activity_dashboard, R.id.parent_coordinator_layout);
  }

  @Override protected void getIntents() {

  }

  @Override protected void setUpComponent() {
    ComponentFactory.getInstance().getDashboardComponent(this).inject(this);
  }

  @Override protected void setupViewHolder(View view) {
    initializeToolbar(getString(R.string.title_dashboard));
    initializeNavigationMenu(getString(R.string.header_navigation_menu));

    new DashboardViewHolder(view);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
    ComponentFactory.getInstance().removeDashboardComponent();
  }

  public void onEventMainThread(MovieCardClickedEvent event) {
    dashboardNavigator.openMovieDetailActivity(this, event.getMovie());
  }
}
