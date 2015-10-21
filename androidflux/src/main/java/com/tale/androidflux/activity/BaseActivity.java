/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.androidflux.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tale.androidflux.Lifecycle;
import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

  /**
   * These 3 fields below is use to determine if bind() method is call from onCreate() or not to
   * call {@link Lifecycle#onViewCreated(Bundle)} method.
   */
  private static final int ON_CREATE = 1;
  private static final int ON_RESUME = 2;
  private int callbackState;

  private List<Lifecycle> lifecycles;
  private Bundle savedInstanceState;

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
    if (callbackState == ON_CREATE) {
      lifeCycle.onViewCreated(savedInstanceState);
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.savedInstanceState = savedInstanceState;
    callbackState = ON_CREATE;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifecycle : lifecycles) {
        lifecycle.onSaveInstanceState(outState);
      }
    }
  }

  @Override protected void onResume() {
    super.onResume();
    callbackState = ON_RESUME;
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifecycle : lifecycles) {
        lifecycle.onResume();
      }
    }
  }

  @Override protected void onPause() {
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifecycle : lifecycles) {
        lifecycle.onPause();
      }
    }
    super.onPause();
  }

  @Override protected void onDestroy() {
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifecycle : lifecycles) {
        lifecycle.onDestroyView();
      }
    }
    lifecycles.clear();
    lifecycles = null;
    super.onDestroy();
  }
}
