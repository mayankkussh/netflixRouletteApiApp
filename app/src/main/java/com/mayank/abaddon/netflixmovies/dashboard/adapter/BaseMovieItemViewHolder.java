package com.mayank.abaddon.netflixmovies.dashboard.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.events.MovieCardClickedEvent;
import com.squareup.picasso.Picasso;
import de.greenrobot.event.EventBus;

/**
 * Created by Mayank.
 */

public abstract class BaseMovieItemViewHolder extends RecyclerView.ViewHolder {

  private Context context;
  private CardView cardView;
  private ImageView movieLogo;
  private TextView movieTitle, summary, duration;
  private Button favoriteAction;

  BaseMovieItemViewHolder(Context context, View itemView) {
    super(itemView);
    this.context = context;
    cardView = (CardView) itemView.findViewById(R.id.card);
    movieLogo = (ImageView) itemView.findViewById(R.id.poster);
    movieTitle = (TextView) itemView.findViewById(R.id.title);
    summary = (TextView) itemView.findViewById(R.id.summary);
    duration = (TextView) itemView.findViewById(R.id.duration);
    favoriteAction = (Button) itemView.findViewById(R.id.favorite_action);
  }

  protected abstract void onFavoriteButtonClickListener(View view);

  void setButtonText(String buttonText) {
    favoriteAction.setText(buttonText);
  }

  void setViewData(Movie movie) {
    if (movie.getPoster() != null) {
      Picasso.with(context).load(movie.getPoster()).placeholder(R.drawable.netflix).into(movieLogo);
    }
    cardView.setOnClickListener(v -> EventBus.getDefault().post(new MovieCardClickedEvent(movie)));
    movieTitle.setText(movie.getShowTitle());
    summary.setText(movie.getSummary());
    duration.setText(movie.getRuntime());
    favoriteAction.setOnClickListener(this::onFavoriteButtonClickListener);
  }
}
