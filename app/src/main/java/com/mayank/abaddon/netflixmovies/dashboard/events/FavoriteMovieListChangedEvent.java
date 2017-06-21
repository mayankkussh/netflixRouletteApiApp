package com.mayank.abaddon.netflixmovies.dashboard.events;

import com.mayank.abaddon.netflixmovies.dashboard.enums.RowModificationType;

/**
 * Created by Mayank.
 */

public class FavoriteMovieListChangedEvent {
  private RowModificationType rowModificationType;

  public FavoriteMovieListChangedEvent(RowModificationType rowModificationType) {
    this.rowModificationType = rowModificationType;
  }

  public RowModificationType getRowModificationType() {
    return rowModificationType;
  }
}
