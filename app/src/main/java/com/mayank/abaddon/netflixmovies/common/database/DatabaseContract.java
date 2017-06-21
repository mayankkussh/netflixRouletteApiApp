package com.mayank.abaddon.netflixmovies.common.database;

/**
 * Created by Mayank.
 */

public class DatabaseContract {

  public static class MovieTable {
    public static final String TABLE_NAME = "movies";
    public static final String _ID = "id";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_SHOW_ID = "showId";
    public static final String COLUMN_SHOW_TITLE = "showTitle";
    public static final String COLUMN_RELEASE_YEAR = "releaseYear";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_CATGORY = "category";
    public static final String COLUMN_SHOWCAST = "showCast";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_SUMMARY = "summary";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_MEDIA_TYPE = "mediaType";
    public static final String COLUMN_RUNTIME = "runtime";
  }
}
