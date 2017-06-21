package com.mayank.abaddon.netflixmovies.common.drawer;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.Toast;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public class MenuUtils {

  @Inject MenuNavigator menuNavigator;

  @Inject MenuUtils() {

  }

  public void applyFontsToNavigationDrawer(NavigationView navigationView, Activity activity) {
    setNavigationDrawerHighlight(activity, navigationView);
  }

  public boolean selectMenuItem(MenuItem menuItem, BaseActivity activity, boolean isTaskRoot) {
    //Check to see which item was being clicked and perform appropriate action
    switch (menuItem.getItemId()) {

      // base activity
      case R.id.dashboard:
        if (!(activity instanceof DashboardActivity)) {
          if (isTaskRoot) {
            menuNavigator.openDashboardActivity(activity);
          }
          activity.finish();
        }
        return true;

      default:
        Toast.makeText(activity, "Something is Wrong", Toast.LENGTH_SHORT).show();
        return false;
    }
  }

  private void setNavigationDrawerHighlight(Activity activity, NavigationView navigationView) {
    int highlightItem = 0;
    // highlight an item in the drawer
    if (activity instanceof DashboardActivity) {
      highlightItem = 0;
    } else {
      navigationView.getMenu().getItem(highlightItem).setCheckable(false);
      navigationView.getMenu().getItem(highlightItem).setChecked(false);
      return;
    }
    navigationView.getMenu().getItem(highlightItem).setCheckable(true);
    navigationView.getMenu().getItem(highlightItem).setChecked(true);
  }
}

