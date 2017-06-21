package com.mayank.abaddon.netflixmovies.dashboard.fragment.search;

import android.app.SearchManager;
import android.content.ComponentName;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.dashboard.enums.ListType;
import com.mayank.abaddon.netflixmovies.dashboard.events.NewMovieFetchedEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.NewMovieListFetchedEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.NoMovieFoundEvent;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.base.BaseMovieFragmentViewHolder;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.common.DashboardFragmentHelper;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Named;

public class MovieSearchFragmentViewHolder extends BaseMovieFragmentViewHolder {

  @Inject @Named("componentName") ComponentName componentName;
  @Inject DashboardFragmentHelper dashboardFragmentHelper;
  @Inject SearchManager searchManager;

  private RadioGroup radioGroup;
  private AppCompatButton clear;
  private TextView searchHint;

  public MovieSearchFragmentViewHolder(View view) {
    super(view);
    initializeMovieList(new ArrayList<>(), ListType.SEARCH);
    EventBus.getDefault().register(this);
    setSearchBoxVisibility(View.VISIBLE);
    setupComponent();
    radioGroup = (RadioGroup) view.findViewById(R.id.search_radio_group);
    clear = (AppCompatButton) view.findViewById(R.id.clear_result_btn);
    searchHint = (TextView) view.findViewById(R.id.search_hint);

    setUpListeners();
  }

  @Override public void unregisterEventBus() {
    EventBus.getDefault().unregister(this);
  }

  public void onEventMainThread(NewMovieFetchedEvent event) {
    movieList.add(0, event.getMovie());
    setDataToRecyclerView(movieList);
  }

  public void onEventMainThread(NewMovieListFetchedEvent event) {
    movieList.addAll(0, event.getMovies());
    setDataToRecyclerView(movieList);
  }

  public void onEventMainThread(NoMovieFoundEvent event) {
    Toast.makeText(context, R.string.no_such_movie_found, Toast.LENGTH_LONG).show();
  }

  void setUpOptionMenu(Menu menu) {
    final MenuItem searchItem = menu.findItem(R.id.action_search_movies);
    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    setupSearch(searchItem, searchView);
  }

  private void setUpListeners() {
    radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
      switch (checkedId) {
        case R.id.rb_title:
          searchHint.setText(R.string.search_by_title);
          break;
        case R.id.rb_director:
          searchHint.setText(R.string.search_by_director);
          break;
        case R.id.rb_actor:
          searchHint.setText(R.string.search_by_actor);
          break;
      }
    });
    clear.setOnClickListener(v -> {
      movieList.clear();
      setDataToRecyclerView(movieList);
    });
  }

  private void setupSearch(MenuItem searchItem, SearchView searchView) {
    searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
    searchView.setQueryHint(context.getString(R.string.search_movies));
    ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);

    closeButton.setOnClickListener(v -> {
      searchView.clearFocus();
      searchView.setQuery("", false);
      searchView.setIconified(true);
      searchItem.collapseActionView();
    });

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        if (!query.isEmpty() && query.length() > 3) {
          dashboardFragmentHelper.searchMovies(query, radioGroup.getCheckedRadioButtonId());
          return true;
        }
        return false;
      }

      @Override public boolean onQueryTextChange(String query) {
        return false;
      }
    });
  }

  private void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }
}
