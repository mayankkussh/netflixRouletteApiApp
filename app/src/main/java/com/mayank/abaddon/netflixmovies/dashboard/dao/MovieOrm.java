package com.mayank.abaddon.netflixmovies.dashboard.dao;

import android.content.ContentValues;
import android.database.Cursor;
import com.mayank.abaddon.netflixmovies.common.database.BaseOrm;
import com.mayank.abaddon.netflixmovies.common.database.DatabaseContract;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public class MovieOrm extends BaseOrm<Movie> {

  @Inject MovieOrm() {

  }

  @Override protected Movie getInstance() {
    return new Movie();
  }

  @Override protected ContentValues setTableFields(ContentValues cv, Movie config) {
    cv.put(DatabaseContract.MovieTable.COLUMN_UNIT, config.getUnit());
    cv.put(DatabaseContract.MovieTable.COLUMN_SHOW_ID, config.getShowId());
    cv.put(DatabaseContract.MovieTable.COLUMN_RELEASE_YEAR, config.getReleaseYear());
    cv.put(DatabaseContract.MovieTable.COLUMN_CATGORY, config.getCategory());
    cv.put(DatabaseContract.MovieTable.COLUMN_RATING, config.getRating());
    cv.put(DatabaseContract.MovieTable.COLUMN_SHOW_TITLE, config.getShowTitle());
    cv.put(DatabaseContract.MovieTable.COLUMN_SHOWCAST, config.getShowCast());
    cv.put(DatabaseContract.MovieTable.COLUMN_DIRECTOR, config.getDirector());
    cv.put(DatabaseContract.MovieTable.COLUMN_SUMMARY, config.getSummary());
    cv.put(DatabaseContract.MovieTable.COLUMN_POSTER, config.getPoster());
    cv.put(DatabaseContract.MovieTable.COLUMN_MEDIA_TYPE, config.getMediatype());
    cv.put(DatabaseContract.MovieTable.COLUMN_RUNTIME, config.getRuntime());
    return cv;
  }

  @Override protected Movie setConfigFields(Movie config, Cursor c) {
    config.setUnit(c.getInt(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_UNIT)));
    config.setShowId(c.getInt(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_SHOW_ID)));
    config.setReleaseYear(
        c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_RELEASE_YEAR)));
    config.setCategory(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_CATGORY)));
    config.setRating(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_RATING)));
    config.setShowTitle(
        c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_SHOW_TITLE)));
    config.setShowCast(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_SHOWCAST)));
    config.setDirector(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_DIRECTOR)));
    config.setSummary(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_SUMMARY)));
    config.setPoster(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_POSTER)));
    config.setMediatype(c.getInt(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_MEDIA_TYPE)));
    config.setRuntime(c.getString(c.getColumnIndex(DatabaseContract.MovieTable.COLUMN_RUNTIME)));
    return config;
  }
}
