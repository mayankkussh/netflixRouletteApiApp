package com.mayank.abaddon.netflixmovies.common.di.http;

import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.common.database.BaseDao;
import com.mayank.abaddon.netflixmovies.common.database.DbHelper;
import com.mayank.abaddon.netflixmovies.dashboard.di.dashboard.DashboardComponent;
import com.mayank.abaddon.netflixmovies.dashboard.di.dashboard.DashboardModule;
import com.mayank.abaddon.netflixmovies.movieDetail.di.MovieDetailComponent;
import com.mayank.abaddon.netflixmovies.movieDetail.di.MovieDetailModule;
import dagger.Subcomponent;

/**
 * Created by Mayank.
 */

@HttpScope @Subcomponent(modules = HttpModule.class) public interface HttpComponent {
  void inject(BaseActivity baseActivity);

  DashboardComponent plus(DashboardModule dashboardModule);

  void inject(DbHelper dbHelper);

  void inject(BaseDao baseDao);

  MovieDetailComponent plus(MovieDetailModule movieDetailModule);
}
