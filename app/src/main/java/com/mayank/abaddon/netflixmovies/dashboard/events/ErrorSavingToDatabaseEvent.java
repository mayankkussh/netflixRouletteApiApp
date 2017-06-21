package com.mayank.abaddon.netflixmovies.dashboard.events;

import com.mayank.abaddon.netflixmovies.dashboard.enums.DatabaseSaveErrorType;

/**
 * Created by Mayank.
 */

public class ErrorSavingToDatabaseEvent {
  private DatabaseSaveErrorType errorType;

  public ErrorSavingToDatabaseEvent(DatabaseSaveErrorType errorType) {
    this.errorType = errorType;
  }

  public DatabaseSaveErrorType getErrorType() {
    return errorType;
  }
}
