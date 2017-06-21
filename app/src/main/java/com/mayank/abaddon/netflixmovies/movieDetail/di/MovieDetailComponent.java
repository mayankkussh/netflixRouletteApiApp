package com.mayank.abaddon.netflixmovies.movieDetail.di;

import com.mayank.abaddon.netflixmovies.movieDetail.MovieDetailViewHolder;
import dagger.Subcomponent;

/**
 * Created by Mayank
 */
@Subcomponent(modules = MovieDetailModule.class) @MovieDetailScope
public interface MovieDetailComponent {
  void inject(MovieDetailViewHolder movieDetailViewHolder);
}
