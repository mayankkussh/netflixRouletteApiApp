package com.mayank.abaddon.netflixmovies.dashboard.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.mayank.abaddon.netflixmovies.R;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import com.mayank.abaddon.netflixmovies.dashboard.adapter.DashboardViewPagerAdapter;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.favorite.FavoriteMovieFragment;
import com.mayank.abaddon.netflixmovies.dashboard.fragment.search.MovieSearchFragment;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Mayank.
 */

public class DashboardViewHolder {

  @Inject @Named("dashboardFragmentManager") FragmentManager fragmentManager;

  private DashboardViewPagerAdapter dashboardViewPagerAdapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public DashboardViewHolder(View view) {
    tabLayout = (TabLayout) view.findViewById(R.id.tabs);
    viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    setupComponent();
    setViewPager();
  }

  private void setViewPager() {
    if (dashboardViewPagerAdapter == null) {
      dashboardViewPagerAdapter = new DashboardViewPagerAdapter(fragmentManager);
    }
    dashboardViewPagerAdapter.clearFragments();

    dashboardViewPagerAdapter.addFragment(MovieSearchFragment.newInstance(), "Search Result");

    dashboardViewPagerAdapter.addFragment(FavoriteMovieFragment.newInstance(), "Favorites");
    viewPager.setAdapter(dashboardViewPagerAdapter);

    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }
}
