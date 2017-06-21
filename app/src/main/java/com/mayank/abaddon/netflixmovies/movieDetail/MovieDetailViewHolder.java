package com.mayank.abaddon.netflixmovies.movieDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Mayank
 */

public class MovieDetailViewHolder {

  @Inject @Named("movie") Movie movie;
  @Inject Context context;

  private ImageView poster;
  private TextView title, releaseYear, rating, category, showCast, director, summary, runtime;

  public MovieDetailViewHolder(View view) {
    ComponentFactory.getInstance().getMovieDetailComponent().inject(this);
    poster = (ImageView) view.findViewById(R.id.poster);
    title = (TextView) view.findViewById(R.id.title);
    releaseYear = (TextView) view.findViewById(R.id.release_year);
    rating = (TextView) view.findViewById(R.id.rating);
    category = (TextView) view.findViewById(R.id.category);
    showCast = (TextView) view.findViewById(R.id.showcast);
    director = (TextView) view.findViewById(R.id.director);
    summary = (TextView) view.findViewById(R.id.summary);
    runtime = (TextView) view.findViewById(R.id.runtime);
    setData();
  }

  private void setData() {
    if (movie.getPoster() != null) {
      Picasso.with(context).load(movie.getPoster()).placeholder(R.drawable.netflix).into(poster);
    }
    title.setText(movie.getShowTitle());
    releaseYear.setText(movie.getReleaseYear());
    rating.setText(movie.getRating());
    category.setText(movie.getCategory());
    showCast.setText(movie.getShowCast());
    director.setText(movie.getDirector());
    summary.setText(movie.getSummary());
    runtime.setText(movie.getRuntime());
  }
}
