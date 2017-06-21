package com.mayank.abaddon.netflixmovies.dashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mayank.abaddon.netflixmovies.common.androidcomponent.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank.
 */
public class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Fragment> mFragmentList = new ArrayList<>();
  private final List<String> mFragmentTitleList = new ArrayList<>();

  public DashboardViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override public int getCount() {
    return mFragmentList.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  public void addFragment(BaseFragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }

  public void clearFragments() {
    mFragmentList.clear();
    mFragmentTitleList.clear();
  }
}
