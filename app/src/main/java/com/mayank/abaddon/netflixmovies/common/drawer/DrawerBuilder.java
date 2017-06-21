package com.mayank.abaddon.netflixmovies.common.drawer;

import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseActivity;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;

/**
 * Created by Mayank.
 */

public class DrawerBuilder {
  private static DrawerBuilder drawerBuilder;

  private DrawerBuilder() {

  }

  public static DrawerBuilder getInstance() {
    if (drawerBuilder == null) {
      drawerBuilder = new DrawerBuilder();
    }
    return drawerBuilder;
  }

  /**
   * @param activity Activity
   * @param navigationView Navifation View
   * @param headerText Text to be displayed on header image
   */
  public void build(final BaseActivity activity, NavigationView navigationView, String headerText) {
    LinearLayout headerView = (LinearLayout) (LayoutInflater.from(activity)
        .inflate(R.layout.navigation_drawer_header, null, false));
    navigationView.addHeaderView(headerView);

    LinearLayout headerLayout =
        (LinearLayout) headerView.findViewById(R.id.navigation_drawer_layout);

    TextView headerTextView = (TextView) headerView.findViewById(R.id.header_text);
    headerTextView.setText(headerText);

    /***
     * mohit header freezbar color according to activity
     */
    setNavigationHeaderColor(activity, headerLayout);

    int pixels = (int) (activity.getResources().getDimension(R.dimen.navigation_menu_header));
    LinearLayout.LayoutParams rel_btn =
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, pixels);
    headerView.setLayoutParams(rel_btn);
  }

  private static void setNavigationHeaderColor(BaseActivity activity, LinearLayout headerLayout) {
    //this.headerLayout = headerLayout;
    if (activity instanceof DashboardActivity) {
      headerLayout.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary));
    }
  }
}
