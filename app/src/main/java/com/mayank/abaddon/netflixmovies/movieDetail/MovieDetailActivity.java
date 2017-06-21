package com.mayank.abaddon.netflixmovies.movieDetail;

import android.os.Bundle;
import android.view.View;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.common.models.Movie;

public class MovieDetailActivity extends BaseActivity {

  private Movie movie;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI(R.layout.activity_movie_detail, R.id.parent_coordinator_layout);
  }

  @Override protected void getIntents() {
    movie = getIntent().getParcelableExtra("movie");
  }

  @Override protected void setUpComponent() {
    ComponentFactory.getInstance().getMovieDetailComponent(movie);
  }

  @Override protected void setupViewHolder(View view) {
    initializeChildActivityToolbar("Movie Detail");
    new MovieDetailViewHolder(view);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ComponentFactory.getInstance().removeMovieDetailComponent();
  }
}
