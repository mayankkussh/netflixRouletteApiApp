package com.mayank.abaddon.netflixmovies.common.database;

/**
 * App schema is here
 * Created by Mayank
 */

class Schema {

  private static final String TEXT_TYPE = " TEXT";
  private static final String INTEGER_TYPE = " INTEGER";
  private static final String CREATE_TABLE = "CREATE TABLE ";
  private static final String COMMA_SEP = ",";
  private static final String AUTOINCREMENT = " AUTOINCREMENT";
  private static final String PRIMARY_KEY = " PRIMARY KEY";

  static final String SQL_CREATE_MOVIE_TABLE = CREATE_TABLE
      + DatabaseContract.MovieTable.TABLE_NAME
      + "( "
      + DatabaseContract.MovieTable._ID
      + INTEGER_TYPE
      + PRIMARY_KEY
      + AUTOINCREMENT
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_UNIT
      + INTEGER_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_SHOW_ID
      + INTEGER_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_RELEASE_YEAR
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_CATGORY
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_RATING
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_SHOW_TITLE
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_SHOWCAST
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_DIRECTOR
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_SUMMARY
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_POSTER
      + TEXT_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_MEDIA_TYPE
      + INTEGER_TYPE
      + COMMA_SEP
      + DatabaseContract.MovieTable.COLUMN_RUNTIME
      + TEXT_TYPE
      + ") ";
}
