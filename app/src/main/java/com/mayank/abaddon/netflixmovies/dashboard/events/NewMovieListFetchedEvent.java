package com.mayank.abaddon.netflixmovies.dashboard.events;

import com.mayank.abaddon.netflixmovies.common.models.Movie;
import java.util.List;

/**
 * Created by Mayank on 21/6/17.
 */

public class NewMovieListFetchedEvent {
  private List<Movie> movies;

  public NewMovieListFetchedEvent(List<Movie> movies) {
    this.movies = movies;
  }

  public List<Movie> getMovies() {
    return movies;
  }
}
