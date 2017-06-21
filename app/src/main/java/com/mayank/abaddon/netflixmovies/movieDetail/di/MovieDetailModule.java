package com.mayank.abaddon.netflixmovies.movieDetail.di;

import com.mayank.abaddon.netflixmovies.common.models.Movie;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by Mayank
 */

@Module public class MovieDetailModule {
  private Movie movie;

  public MovieDetailModule(Movie movie) {
    this.movie = movie;
  }

  @Provides @Named("movie") Movie providesMovie() {
    return movie;
  }
}
