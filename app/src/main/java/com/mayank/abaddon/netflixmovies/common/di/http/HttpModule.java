package com.mayank.abaddon.netflixmovies.common.di.http;

import android.content.Context;
import com.mayank.abaddon.netflixmovies.common.retrofit.RetrofitBuilder;
import com.mayank.abaddon.netflixmovies.common.retrofit.service.HttpApiService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by Mayank.
 */

@Module public class HttpModule {

  @HttpScope @Provides @Named("httpServiceBackground")
  public HttpApiService providesBackgroundThreadHttpService(Context context,
      RetrofitBuilder retrofitBuilder) {

    return retrofitBuilder.getHttpServiceBackgroundThread(context);
  }

  @HttpScope @Provides @Named("httpServiceSameThread")
  public HttpApiService providesSameThreadHttpService(Context context,
      RetrofitBuilder retrofitBuilder) {

    return retrofitBuilder.getHttpServiceSameThread(context);
  }
}
