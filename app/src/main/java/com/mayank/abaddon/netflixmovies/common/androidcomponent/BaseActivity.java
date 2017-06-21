package com.mayank.abaddon.netflixmovies.common.androidcomponent;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.common.drawer.DrawerBuilder;
import com.mayank.abaddon.netflixmovies.common.drawer.MenuUtils;
import com.mayank.abaddon.netflixmovies.dashboard.activity.DashboardActivity;
import javax.inject.Inject;

/**
 * Created by Mayank.
 */

public abstract class BaseActivity extends AppCompatActivity {
  private final String TAG = this.getClass().getSimpleName();
  @Inject MenuUtils menuUtils;
  @Inject BaseActivityNavigator baseActivityNavigator;
  private Toolbar toolbar;
  private NavigationView navigationView;
  private DrawerLayout drawerLayout;
  private int backPressCount = 0;

  @Override public void onBackPressed() {
    if (drawerLayout != null && drawerLayout.isDrawerOpen(navigationView)) {
      drawerLayout.closeDrawers();
      backPressCount = 0;
    } else if (!(this instanceof DashboardActivity)) { //closes other activities
      if (isTaskRoot()) {
        baseActivityNavigator.openDashboardActivity(this);
        finish();
      } else {
        Log.d(TAG, "dashboardActivity");
        super.onBackPressed();
      }
    } else {
      backPressCount++;
      if (backPressCount >= 2) {
        super.onBackPressed();
      } else {
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show();
      }
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ComponentFactory.getInstance().getNetflixComponent().inject(this);
  }

  protected void setupUI(int layoutId, int viewHolderId) {
    setContentView(layoutId);
    getIntents();
    setUpComponent();
    setupViewHolder(findViewById(viewHolderId));
  }

  //Receives the intent values for this activity
  protected abstract void getIntents();

  protected abstract void setUpComponent();

  //initialize the data to the view and other variables
  abstract protected void setupViewHolder(View view);

  protected void initializeChildActivityToolbar(String title) {
    initializeToolbar(title);
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(v -> onBackArrowUp());
  }

  protected void initializeToolbar(String title) {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(title);
    toolbar.setTitleTextColor(0xffffffff);
    setSupportActionBar(toolbar);
  }

  protected void initializeNavigationMenu(String headerText) {
    navigationView = (NavigationView) findViewById(R.id.navigation_view);

    //Highlight the first menu item ie "Menu" since by default app will open on Menu screen
    navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

    // Initializing Drawer Layout and ActionBarToggle
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
    DrawerBuilder.getInstance().build(this, navigationView, headerText);
    createDrawerToolbar(toolbar);

    setupNavigation();
  }

  private void onBackArrowUp() {
    if (isTaskRoot()) {
      baseActivityNavigator.openDashboardActivity(this);
    }
    finish();
  }

  private void createDrawerToolbar(Toolbar toolbar) {
    ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer,
            R.string.close_drawer) {

          @Override public void onDrawerOpened(View drawerView) {
            // Code here will be triggered once the drawer open as we don't want anything to happen so we
            // leave this blank
            super.onDrawerOpened(drawerView);
          }

          @Override public void onDrawerClosed(View drawerView) {
            // Code here will be triggered once the drawer closes as we don't want anything to happen so we
            // leave this blank
            super.onDrawerClosed(drawerView);
          }
        };

    //Setting the actionbarToggle to drawer layout
    drawerLayout.addDrawerListener(actionBarDrawerToggle);

    //calling sync state is necessary or else your hamburger icon wont show up
    actionBarDrawerToggle.syncState();
  }

  private void setupNavigation() {
    menuUtils.applyFontsToNavigationDrawer(navigationView, this);
    navigationView.setNavigationItemSelectedListener(item -> {
      //Closing drawer on item click
      drawerLayout.closeDrawers();
      return menuUtils.selectMenuItem(item, BaseActivity.this, isTaskRoot());
    });
  }
}
