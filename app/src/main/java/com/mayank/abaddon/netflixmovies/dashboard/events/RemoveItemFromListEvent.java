package com.mayank.abaddon.netflixmovies.dashboard.events;

import com.mayank.abaddon.netflixmovies.common.models.Movie;

/**
 * Created by Mayank.
 */

public class RemoveItemFromListEvent {
  private Movie item;

  public RemoveItemFromListEvent(Movie item) {
    this.item = item;
  }

  public Movie getItem() {
    return item;
  }
}
