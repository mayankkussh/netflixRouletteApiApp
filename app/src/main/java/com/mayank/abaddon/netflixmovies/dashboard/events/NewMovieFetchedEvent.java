package com.mayank.abaddon.netflixmovies.dashboard.events;

import com.mayank.abaddon.netflixmovies.common.models.Movie;

/**
 * Created by Mayank.
 */

public class NewMovieFetchedEvent {
  private Movie movie;

  public NewMovieFetchedEvent(Movie movie) {
    this.movie = movie;
  }

  public Movie getMovie() {
    return movie;
  }
}
