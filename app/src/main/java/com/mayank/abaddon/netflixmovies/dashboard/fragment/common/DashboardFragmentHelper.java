package com.mayank.abaddon.netflixmovies.dashboard.fragment.common;

import android.util.Log;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.models.Movie;
import com.mayank.abaddon.netflixmovies.common.retrofit.service.HttpApiService;
import com.mayank.abaddon.netflixmovies.dashboard.dao.MovieDaoHelper;
import com.mayank.abaddon.netflixmovies.dashboard.enums.DatabaseSaveErrorType;
import com.mayank.abaddon.netflixmovies.dashboard.events.ErrorSavingToDatabaseEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.NewMovieFetchedEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.NewMovieListFetchedEvent;
import com.mayank.abaddon.netflixmovies.dashboard.events.NoMovieFoundEvent;
import com.mayank.abaddon.netflixmovies.dashboard.exception.ElementAlreadyExistException;
import de.greenrobot.event.EventBus;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mayank.
 */

public class DashboardFragmentHelper {

  @Inject @Named("httpServiceSameThread") HttpApiService movieApiService;

  @Inject MovieDaoHelper movieDaoHelper;

  @Inject DashboardFragmentHelper() {

  }

  public void searchMovies(String query, int checkedRadioButtonId) {
    switch (checkedRadioButtonId) {
      case R.id.rb_title:
        requestIndividualMovieCall(movieApiService.getMovieByTitle(query));
        break;
      case R.id.rb_director:
        requestListOfMovieCall(movieApiService.getResultByDirector(query));
        break;
      case R.id.rb_actor:
        requestListOfMovieCall(movieApiService.getResultByActor(query));
        break;
    }
  }

  public void addElementToList(Movie movie) {
    try {
      movieDaoHelper.addItemToList(movie);
    } catch (ElementAlreadyExistException e) {
      EventBus.getDefault()
          .post(new ErrorSavingToDatabaseEvent(DatabaseSaveErrorType.ITEM_ALREADY_EXIST));
    }
  }

  public void removeElementFromList(Movie item) {
    movieDaoHelper.removeItemFromList(item);
  }

  private void requestIndividualMovieCall(Call<Movie> titleApi) {
    titleApi.enqueue(new Callback<Movie>() {
      @Override public void onResponse(Call<Movie> call, Response<Movie> response) {
        if (response.code() == 200) {
          if (response.body() != null) {
            EventBus.getDefault().post(new NewMovieFetchedEvent(response.body()));
          }
        }
      }

      @Override public void onFailure(Call<Movie> call, Throwable t) {
        onMovieResultFailure();
      }
    });
  }

  private void requestListOfMovieCall(Call<List<Movie>> api) {
    api.enqueue(new Callback<List<Movie>>() {
      @Override public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
        if (response.code() == 200) {
          if (response.body() != null) {
            EventBus.getDefault().post(new NewMovieListFetchedEvent(response.body()));
          }
        }
      }

      @Override public void onFailure(Call<List<Movie>> call, Throwable t) {
        onMovieResultFailure();
      }
    });
  }

  private void onMovieResultFailure() {
    Log.e("error", "Data Not Found");
    EventBus.getDefault().post(new NoMovieFoundEvent());
  }
}
