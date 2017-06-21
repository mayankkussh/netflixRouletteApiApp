package com.mayank.abaddon.netflixmovies.common.di.master;

import com.mayank.abaddon.netflixmovies.common.androidcomponent.MovieApplication;
import com.mayank.abaddon.netflixmovies.common.di.http.HttpComponent;
import com.mayank.abaddon.netflixmovies.common.di.http.HttpModule;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;
import com.mayank.abaddon.netflixmovies.dashboard.di.dashboard.DashboardComponent;
import com.mayank.abaddon.netflixmovies.dashboard.di.dashboard.DashboardModule;
import com.mayank.abaddon.netflixmovies.dashboard.di.favmovie.FavoriteMovieComponent;
import com.mayank.abaddon.netflixmovies.movieDetail.di.MovieDetailComponent;
import com.mayank.abaddon.netflixmovies.movieDetail.di.MovieDetailModule;

/**
 * Created by Mayank.
 */

public class ComponentFactory {

  private static ComponentFactory componentFactory;

  private NetflixComponent netflixComponent;

  private HttpComponent httpComponent;

  private DashboardComponent dashboardComponent;

  private MovieDetailComponent movieDetailComponent;

  public static ComponentFactory getInstance() {
    if (componentFactory == null) {
      componentFactory = new ComponentFactory();
    }
    return componentFactory;
  }

  public void initializeComponent(MovieApplication movieApplication) {
    netflixComponent =
        DaggerNetflixComponent.builder().appModule(new AppModule(movieApplication)).build();
  }

  public NetflixComponent getNetflixComponent() {
    return netflixComponent;
  }

  public HttpComponent getHttpComponent() {
    if (httpComponent == null) {
      httpComponent = getNetflixComponent().plus(new HttpModule());
    }
    return httpComponent;
  }

  public DashboardComponent getDashboardComponent(DashboardActivity activity) {
    if (dashboardComponent == null) {
      dashboardComponent = getHttpComponent().plus(new DashboardModule(activity));
    }
    return dashboardComponent;
  }

  public FavoriteMovieComponent getFavoriteMovieComponent() {
    return getDashboardComponent().plusFavoriteMovieComponent();
  }

  public DashboardComponent getDashboardComponent() {
    return dashboardComponent;
  }

  public void removeDashboardComponent() {
    dashboardComponent = null;
  }

  public MovieDetailComponent getMovieDetailComponent(Movie movie) {
    if (movieDetailComponent == null) {
      movieDetailComponent = getHttpComponent().plus(new MovieDetailModule(movie));
    }
    return movieDetailComponent;
  }

  public MovieDetailComponent getMovieDetailComponent() {
    return movieDetailComponent;
  }

  public void removeMovieDetailComponent() {
    movieDetailComponent = null;
  }
}
