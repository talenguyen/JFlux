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
import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

  private List<Lifecycle> lifecycles;

  /**
   * Bind lifecycle to this fragment. Should be called bin onCreateView() method.
   *
   * @param lifeCycle the {@link Lifecycle} object.
   */
  public void bind(Lifecycle lifeCycle) {
    if (lifecycles == null) {
      lifecycles = new ArrayList<>();
    }
    if (!lifecycles.contains(lifeCycle)) {
      lifecycles.add(lifeCycle);
    }
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifeCycle : lifecycles) {
        lifeCycle.onRestoreInstanceState(savedInstanceState);
      }
    }
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifeCycle : lifecycles) {
        lifeCycle.onSaveInstanceState(outState);
      }
    }
  }

  @Override public void onResume() {
    super.onResume();
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifeCycle : lifecycles) {
        lifeCycle.onResume();
      }
    }
  }

  @Override public void onPause() {
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifeCycle : lifecycles) {
        lifeCycle.onPause();
      }
    }
    super.onPause();
  }

  @Override public void onDestroyView() {
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifeCycle : lifecycles) {
        lifeCycle.onDestroyView();
      }
    }
    super.onDestroyView();
  }
}
