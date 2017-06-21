package com.mayank.abaddon.netflixmovies.dashboard.fragment.favorite;

import android.view.View;
import android.widget.Toast;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.enums.ListType;
import com.mayank.abaddon.netflixmovies.dashboard.events.AddItemToFavListEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.ErrorSavingToDatabaseEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.FavoriteMovieListChangedEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.RemoveItemFromListEvent;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.base.BaseMovieFragmentViewHolder;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.common.DashboardFragmentHelper;
import de.greenrobot.event.EventBus;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Mayank.
 */

public class FavoriteMovieFragmentViewHolder extends BaseMovieFragmentViewHolder {

  @Inject @Named("favoriteMovieList") List<Movie> movieList;

  @Inject DashboardFragmentHelper dashboardFragmentHelper;

  FavoriteMovieFragmentViewHolder(View view) {
    super(view);
    EventBus.getDefault().register(this);
    setUpComponent();
    setSearchBoxVisibility(View.GONE);
    initializeMovieList(movieList, ListType.FAVORITE);
  }

  @Override public void unregisterEventBus() {
    EventBus.getDefault().unregister(this);
  }

  public void onEventMainThread(FavoriteMovieListChangedEvent event) {
    switch (event.getRowModificationType()) {
      case ADD:
        Toast.makeText(context, R.string.added_to_favorite_list, Toast.LENGTH_LONG).show();
        break;
      case REMOVE:
        Toast.makeText(context, R.string.removed_from_favorite_list, Toast.LENGTH_LONG).show();
        break;
    }
    refreshData();
  }

  public void onEventBackgroundThread(AddItemToFavListEvent event) {
    dashboardFragmentHelper.addElementToList(event.getItem());
  }

  public void onEventMainThread(ErrorSavingToDatabaseEvent event) {
    switch (event.getErrorType()) {
      case ITEM_ALREADY_EXIST:
        Toast.makeText(context, R.string.movie_already_in_favorites, Toast.LENGTH_LONG).show();
        break;
    }
  }

  public void onEventBackgroundThread(RemoveItemFromListEvent event) {
    dashboardFragmentHelper.removeElementFromList(event.getItem());
  }

  private void refreshData() {
    setUpComponent();
    setDataToRecyclerView(movieList);
  }

  private void setUpComponent() {
    ComponentFactory.getInstance().getFavoriteMovieComponent().inject(this);
  }
}
