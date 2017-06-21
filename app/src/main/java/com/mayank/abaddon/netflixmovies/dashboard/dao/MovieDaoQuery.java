package com.mayank.abaddon.netflixmovies.dashboard.dao;

import com.mayank.abaddon.netflixmovies.common.database.DatabaseContract;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public class MovieDaoQuery {
  @Inject MovieDaoQuery() {

  }

  public String getDeleteMovieQuery(Integer showId) {
    return DatabaseContract.MovieTable.COLUMN_SHOW_ID + " = " + showId;
  }

  public String getFavMovieListQuery() {
    return " select * from " + DatabaseContract.MovieTable.TABLE_NAME;
  }

  public String getMovieDetailQuery(Integer showId) {
    return "select * from "
        + DatabaseContract.MovieTable.TABLE_NAME
        + " where "
        + DatabaseContract.MovieTable.COLUMN_SHOW_ID
        + " = "
        + showId;
  }

  public String getElementCountInTableQuery(Integer showId) {
    return "select count("
        + DatabaseContract.MovieTable._ID
        + ") from "
        + DatabaseContract.MovieTable.TABLE_NAME
        + " where "
        + DatabaseContract.MovieTable.COLUMN_SHOW_ID
        + " = "
        + showId;
  }
}
