package com.mayank.abaddon.netflixmovies.dashboard.dao;

import android.content.Context;
import com.mayank.abaddon.netflixmovies.common.database.BaseDao;
import com.mayank.abaddon.netflixmovies.common.database.DatabaseContract;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public class MovieDao extends BaseDao {

  @Inject Context context;

  @Inject MovieOrm movieOrm;

  @Inject MovieDaoQuery query;

  @Inject MovieDao() {

  }

  public long addToDatabase(Movie item) {
    return getWriteableDatabase().insert(DatabaseContract.MovieTable.TABLE_NAME, null,
        movieOrm.providesContentValues(item));
  }

  public long removeFromDatabase(Movie item) {
    return getWriteableDatabase().delete(DatabaseContract.MovieTable.TABLE_NAME,
        query.getDeleteMovieQuery(item.getShowId()), null);
  }

  public List<Movie> getFavoriteMovieList() {
    List<Movie> movies = new ArrayList<>();
    runRawQuery(query.getFavMovieListQuery(),
        cursor -> movies.add(movieOrm.providesConfig(cursor)));
    return movies;
  }

  public Movie getMovieDetail(Integer showId) {
    return getFirstRecord(query.getMovieDetailQuery(showId), from -> movieOrm.providesConfig(from));
  }

  public Integer getItemWithId(Integer showId) {
    return getFirstRecord(query.getElementCountInTableQuery(showId), from -> from.getInt(0));
  }
}
