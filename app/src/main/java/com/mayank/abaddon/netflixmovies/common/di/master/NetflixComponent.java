package com.mayank.abaddon.netflixmovies.common.di.master;

import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.common.di.http.HttpComponent;
import com.mayank.abaddon.netflixmovies.common.di.http.HttpModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Mayank.
 */

@Singleton @Component(modules = AppModule.class) public interface NetflixComponent {

  HttpComponent plus(HttpModule httpModule);

  void inject(BaseActivity baseActivity);
}
