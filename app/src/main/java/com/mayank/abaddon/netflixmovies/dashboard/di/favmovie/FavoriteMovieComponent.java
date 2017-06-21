package com.mayank.abaddon.netflixmovies.dashboard.di.favmovie;

import com.mayank.abaddon.netflixmovies.dashboard.fragment.favorite.FavoriteMovieFragmentViewHolder;
import dagger.Subcomponent;

/**
 * Created by Mayank.
 */

@Subcomponent(modules = FavoriteMovieModule.class) @FavoriteMovieScope
public interface FavoriteMovieComponent {
  void inject(FavoriteMovieFragmentViewHolder favoriteMovieFragmentViewHolder);
}
