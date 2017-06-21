package com.mayank.abaddon.netflixmovies.dashboard.fragment.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.adapter.MovieListAdapter;
import com.mayank.abaddon.netflixmovies.dashboard.enums.ListType;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public abstract class BaseMovieFragmentViewHolder {
  @Inject public Context context;
  protected List<Movie> movieList;
  private MovieListAdapter movieListAdapter;
  private RecyclerView movieRecyclerView;
  private LinearLayout searchBox;

  public BaseMovieFragmentViewHolder(View view) {
    searchBox = (LinearLayout) view.findViewById(R.id.search_box);
    movieRecyclerView = (RecyclerView) view.findViewById(R.id.movieListView);
  }

  public abstract void unregisterEventBus();

  protected void setSearchBoxVisibility(int visibility) {
    searchBox.setVisibility(visibility);
  }

  protected void initializeMovieList(List<Movie> moviesList, ListType listType) {
    this.movieList = moviesList;
    movieListAdapter = new MovieListAdapter(moviesList, listType);
    setupRecyclerView();
  }

  protected void setDataToRecyclerView(List<Movie> moviesList) {
    if (moviesList != null) {
      movieListAdapter.setData(moviesList);
    }
  }

  private void setupRecyclerView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    movieRecyclerView.setLayoutManager(linearLayoutManager);
    movieRecyclerView.setAdapter(movieListAdapter);
  }
}
