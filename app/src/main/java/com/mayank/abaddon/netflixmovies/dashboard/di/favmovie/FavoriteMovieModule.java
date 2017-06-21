package com.mayank.abaddon.netflixmovies.dashboard.di.favmovie;

import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.dao.MovieDaoHelper;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Named;

/**
 * Created by Mayank.
 */
@Module public class FavoriteMovieModule {

  @Provides @FavoriteMovieScope @Named("favoriteMovieList") List<Movie> providesMovieList(
      MovieDaoHelper mergeDaoHelper) {
    return mergeDaoHelper.getFavoriteMovieList();
  }
}
