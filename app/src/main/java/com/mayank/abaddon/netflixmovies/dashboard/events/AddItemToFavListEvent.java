package com.mayank.abaddon.netflixmovies.dashboard.events;

import com.mayank.abaddon.netflixmovies.common.models.Movie;

/**
 * Created by Mayank.
 */

public class AddItemToFavListEvent {
  private Movie item;

  public AddItemToFavListEvent(Movie item) {
    this.item = item;
  }

  public Movie getItem() {
    return item;
  }
}
