package com.mayank.abaddon.netflixmovies.dashboard.fragment.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseFragment;

/**
 * Created by Mayank.
 */

public class FavoriteMovieFragment extends BaseFragment {

  private FavoriteMovieFragmentViewHolder favoriteMovieFragmentViewHolder;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(false);
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return setupUI(inflater, container, R.layout.fragment_dashboard);
  }

  @Override protected void setupViewHolder(View view) {
    favoriteMovieFragmentViewHolder = new FavoriteMovieFragmentViewHolder(view);
  }

  @Override public void onDetach() {
    super.onDetach();
    favoriteMovieFragmentViewHolder.unregisterEventBus();
  }


  public static FavoriteMovieFragment newInstance() {
    Bundle args = new Bundle();
    FavoriteMovieFragment fragment = new FavoriteMovieFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
