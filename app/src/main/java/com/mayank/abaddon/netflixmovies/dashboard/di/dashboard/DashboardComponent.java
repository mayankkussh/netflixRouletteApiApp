package com.mayank.abaddon.netflixmovies.dashboard.di.dashboard;

import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardViewHolder;
import com.mayank.abaddon.netflixmovies.dashboard.adapter.MovieListAdapter;
import com.mayank.abaddon.netflixmovies.dashboard.di.favmovie.FavoriteMovieComponent;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.search.MovieSearchFragmentViewHolder;
import dagger.Subcomponent;

/**
 * Created by Mayank
 */
@Subcomponent(modules = DashboardModule.class) @DashboardScope public interface DashboardComponent {
  void inject(DashboardViewHolder dashboardViewHolder);

  void inject(MovieListAdapter movieListAdapter);

  void inject(MovieSearchFragmentViewHolder movieSearchFragmentViewHolder);

  FavoriteMovieComponent plusFavoriteMovieComponent();

  void inject(DashboardActivity dashboardActivity);
}
