package com.mayank.abaddon.netflixmovies.common.drawer;

import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivityNavigator;
import javax.inject.Inject;

/**
 * Created by Mayank
 */

class MenuNavigator extends BaseActivityNavigator {

  @Inject MenuNavigator() {

  }

  public void openDashboardActivity(BaseActivity activity) {
    super.openDashboardActivity(activity);
  }
}
