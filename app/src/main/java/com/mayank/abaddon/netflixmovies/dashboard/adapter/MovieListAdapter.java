package com.mayank.abaddon.netflixmovies.dashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.dashboard.enums.ListType;
import com.mayank.abaddon.netflixmovies.dashboard.events.AddItemToFavListEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.RemoveItemFromListEvent;
import de.greenrobot.event.EventBus;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public class MovieListAdapter extends RecyclerView.Adapter<BaseMovieItemViewHolder> {

  @Inject Context context;
  private List<Movie> movieList;
  private ListType listType;

  public MovieListAdapter(List<Movie> MovieList, ListType listType) {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
    this.movieList = MovieList;
    this.listType = listType;
  }

  @Override public BaseMovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_card, parent, false);
    switch (listType) {
      case SEARCH:
        return new SearchMovieItemViewHolder(context, v);
      case FAVORITE:
        return new FavoriteMovieItemViewHolder(context, v);
    }
    return new SearchMovieItemViewHolder(context, v);
  }

  @Override public int getItemCount() {
    return movieList.size();
  }

  @Override public void onBindViewHolder(BaseMovieItemViewHolder holder, int position) {
    Movie Movie = movieList.get(position);
    holder.setViewData(Movie);
  }

  public void setData(List<Movie> Movie) {
    this.movieList = Movie;
    notifyDataSetChanged();
  }

  private class SearchMovieItemViewHolder extends BaseMovieItemViewHolder {

    SearchMovieItemViewHolder(Context context, View itemView) {
      super(context, itemView);
      setButtonText(context.getString(R.string.add_to_favorite));
    }

    protected void onFavoriteButtonClickListener(View view) {
      EventBus.getDefault().post(new AddItemToFavListEvent(movieList.get(getAdapterPosition())));
    }
  }

  private class FavoriteMovieItemViewHolder extends BaseMovieItemViewHolder {

    FavoriteMovieItemViewHolder(Context context, View itemView) {
      super(context, itemView);
      setButtonText(context.getString(R.string.remove_from_favorite));
    }

    protected void onFavoriteButtonClickListener(View view) {
      EventBus.getDefault().post(new RemoveItemFromListEvent(movieList.get(getAdapterPosition())));
    }
  }
}
