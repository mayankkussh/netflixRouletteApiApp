package com.mayank.abaddon.netflixmovies.dashboard.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseFragment;

/**
 * Created by Mayank.
 */
public class MovieSearchFragment extends BaseFragment {
  private MovieSearchFragmentViewHolder movieSearchFragmentViewHolder;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return setupUI(inflater, container, R.layout.fragment_dashboard);
  }

  @Override protected void setupViewHolder(View view) {
    movieSearchFragmentViewHolder = new MovieSearchFragmentViewHolder(view);
  }

  @Override public void onDetach() {
    super.onDetach();
    movieSearchFragmentViewHolder.unregisterEventBus();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_dashboard, menu);
    movieSearchFragmentViewHolder.setUpOptionMenu(menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  public static MovieSearchFragment newInstance() {
    Bundle args = new Bundle();
    MovieSearchFragment fragment = new MovieSearchFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
