/*
 *
 *  * Copyright (c) N2Tech Pvt. Ltd. - All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Written by N2Tech 14/3/2017
 *
 */

package com.mayank.abaddon.netflixmovies.common.retrofit.interceptors;

import android.util.Log;
import java.io.IOException;
import java.util.Date;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mayank.
 */

public class HeaderInterceptor implements Interceptor {
  private static final String TAG = HeaderInterceptor.class.getSimpleName();

  public HeaderInterceptor() {
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();
    Request.Builder requestBuilder =
        original.newBuilder().method(original.method(), original.body());
    if (original.method().equals("GET")) {
      requestBuilder = requestBuilder.addHeader("Cache-Control", "no-cache");
    }
    Request request = requestBuilder.build();
    Response response = chain.proceed(request);
    String url = request.url().toString();
    if (response.isSuccessful()) {
      handleSuccess(response);
    } else {
      handleError(new IOException(response.message()), url);
    }
    return response;
  }

  private void handleError(IOException e, String service) {
    Log.d(TAG, e.toString());
    Log.d(TAG, "endService error " + service + " : " + (new Date()).getTime());
  }

  private void handleSuccess(Response response) {
    String responseString = response.body().toString();
    Log.d("http", "endService "
        + response.request().url()
        + " : "
        + (new Date()).getTime()
        + ":"
        + responseString);
  }
}

