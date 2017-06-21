package com.mayank.abaddon.netflixmovies.common.androidcomponent;

import android.app.Application;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;

/**
 * Created by Mayank.
 */

public class MovieApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    ComponentFactory.getInstance().initializeComponent(this);
  }
}
