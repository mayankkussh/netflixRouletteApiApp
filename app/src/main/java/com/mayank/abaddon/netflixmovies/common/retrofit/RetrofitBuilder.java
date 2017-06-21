package com.mayank.abaddon.netflixmovies.common.retrofit;

import android.content.Context;
import android.support.compat.BuildConfig;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.retrofit.interceptors.HeaderInterceptor;
import com.mayank.abaddon.netflixmovies.common.retrofit.service.HttpApiService;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayank.
 */
public class RetrofitBuilder {

  private OkHttpClient httpClientWithoutGzipNoTimeOut;

  @Inject public RetrofitBuilder() {

  }

  /**
   * network service to make a request and the callback will be executed on the background thread
   *
   * @param context context of the app
   * @return a HttpApiService object
   */
  public HttpApiService getHttpServiceBackgroundThread(Context context) {
    return getRetrofitBackgroundThread(context).create(HttpApiService.class);
  }

  /**
   * network service to make a request and the callback will be executed on the calling thread
   *
   * @param context context of the app
   * @return a HttpApiService object
   */
  public HttpApiService getHttpServiceSameThread(Context context) {
    return getRetrofitSameThread(context).create(HttpApiService.class);
  }

  private Retrofit getRetrofitBackgroundThread(Context context) {
    return getRetrofit(context, true);
  }

  private Retrofit getRetrofitSameThread(Context context) {
    return getRetrofit(context, false);
  }

  private OkHttpClient getOkHttpClientNoTimeOut() {
    if (httpClientWithoutGzipNoTimeOut == null) {
      httpClientWithoutGzipNoTimeOut = getHttpClient();
    }
    return httpClientWithoutGzipNoTimeOut;
  }

  private Retrofit getRetrofit(Context context, boolean onBackgroundThread) {
    Retrofit.Builder builder = new Retrofit.Builder();
    builder.client(getOkHttpClientNoTimeOut());
    if (onBackgroundThread) {
      builder.callbackExecutor(Executors.newFixedThreadPool(5));
    }
    builder.baseUrl(context.getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create());
    return builder.build();
  }

  // create an okhttp client
  private OkHttpClient getHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder().retryOnConnectionFailure(false);
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      builder.addInterceptor(logging);
      //builder.addNetworkInterceptor(new com.facebook.stetho.okhttp3.StethoInterceptor());
    }
    builder.addInterceptor(new HeaderInterceptor());
    return builder.build();
  }
}
