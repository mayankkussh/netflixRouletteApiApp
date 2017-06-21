package com.mayank.abaddon.netflixmovies.common.retrofit.service;

import com.mayank.abaddon.netflixmovies.common.models.Movie;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mayank.
 */

public interface HttpApiService {
  @GET("api/api.php") Call<Movie> getMovieByTitle(@Query("title") String query);

  @GET("api/api.php")Call<List<Movie>> getResultByDirector(@Query("director")String query);

  @GET("api/api.php")Call<List<Movie>> getResultByActor(@Query("actor")String query);
}
