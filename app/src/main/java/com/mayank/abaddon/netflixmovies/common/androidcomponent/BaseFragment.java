package com.mayank.abaddon.netflixmovies.common.androidcomponent;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mayank.
 */

public abstract class BaseFragment extends Fragment {

  protected View setupUI(LayoutInflater inflater, ViewGroup container, int layoutId) {
    View fragmentView = inflater.inflate(layoutId, container, false);
    setupViewHolder(fragmentView);
    return fragmentView;
  }

  abstract protected void setupViewHolder(View view);

}
