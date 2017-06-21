package com.mayank.abaddon.netflixmovies.common.androidcomponent;

import android.content.Intent;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;
import javax.inject.Inject;

/**
 * Created by Mayank
 */

public class BaseActivityNavigator {

  @Inject public BaseActivityNavigator() {

  }

  public void openDashboardActivity(BaseActivity baseActivity) {
    Intent intent = new Intent(baseActivity, DashboardActivity.class);
    baseActivity.startActivity(intent);
  }
}
