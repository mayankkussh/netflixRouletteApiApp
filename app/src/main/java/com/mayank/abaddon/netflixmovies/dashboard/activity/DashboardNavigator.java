package com.mayank.abaddon.netflixmovies.dashboard.activity;

import android.content.Intent;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.movieDetail.MovieDetailActivity;
import javax.inject.Inject;

/**
 * Created by Mayank
 */

public class DashboardNavigator {
  @Inject DashboardNavigator() {

  }

  public void openMovieDetailActivity(DashboardActivity dashboardActivity, Movie movie) {
    Intent intent = new Intent(dashboardActivity, MovieDetailActivity.class);
    intent.putExtra("movie", movie);
    dashboardActivity.startActivity(intent);
  }
}
