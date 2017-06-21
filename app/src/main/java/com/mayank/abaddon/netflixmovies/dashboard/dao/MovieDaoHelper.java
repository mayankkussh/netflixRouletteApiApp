package com.mayank.abaddon.netflixmovies.dashboard.dao;

import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.enums.RowModificationType;
import com.mayank.abaddon.netflixmovies.dashboard.events.FavoriteMovieListChangedEvent;
import com.mayank.abaddon.netflixmovies.dashboard.exception.ElementAlreadyExistException;
import de.greenrobot.event.EventBus;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public class MovieDaoHelper {

  @Inject MovieDao movieDao;

  @Inject public MovieDaoHelper() {

  }

  public void addItemToList(Movie item) throws ElementAlreadyExistException {
    if (!getIfItemExists(item.getShowId())) {
      long rowsAdded = movieDao.addToDatabase(item);
      if (rowsAdded > 0) {
        EventBus.getDefault().post(new FavoriteMovieListChangedEvent(RowModificationType.ADD));
      }
    } else {
      throw new ElementAlreadyExistException();
    }
  }

  public void removeItemFromList(Movie item) {
    long rowsRemoved = movieDao.removeFromDatabase(item);
    if (rowsRemoved > 0) {
      EventBus.getDefault().post(new FavoriteMovieListChangedEvent(RowModificationType.REMOVE));
    }
  }

  public List<Movie> getFavoriteMovieList() {
    return movieDao.getFavoriteMovieList();
  }

  private boolean getIfItemExists(Integer showId) {
    Integer itemCount = movieDao.getItemWithId(showId);
    return itemCount != null && itemCount > 0;
  }
}
