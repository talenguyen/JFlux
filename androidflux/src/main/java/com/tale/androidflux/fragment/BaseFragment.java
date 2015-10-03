/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.androidflux.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tale.androidflux.Lifecycle;

public class BaseFragment extends Fragment {

  private Lifecycle lifecycle;

  /**
   * Bind lifecycle to this fragment. Should be called bin onCreateView() method.
   *
   * @param lifecycle the {@link Lifecycle} object.
   */
  public void bindLifeCycle(Lifecycle lifecycle) {
    this.lifecycle = lifecycle;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (lifecycle != null) {
      lifecycle.onRestoreInstanceState(savedInstanceState);
    }
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    if (lifecycle != null) {
      lifecycle.onSaveInstanceState(outState);
    }
  }

  @Override public void onResume() {
    super.onResume();
    if (lifecycle != null) {
      lifecycle.onResume();
    }
  }

  @Override public void onPause() {
    if (lifecycle != null) {
      lifecycle.onPause();
    }
    super.onPause();
  }

  @Override public void onDestroyView() {
    if (lifecycle != null) {
      lifecycle.onDestroyView();
    }
    super.onDestroyView();
  }
}
