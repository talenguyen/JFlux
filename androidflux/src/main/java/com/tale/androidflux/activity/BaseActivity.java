/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.androidflux.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.tale.androidflux.Lifecycle;
import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

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

  @Override protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (lifecycles != null && lifecycles.size() > 0) {
      for (Lifecycle lifecycle : lifecycles) {
        lifecycle.onRestoreInstanceState(savedInstanceState);
      }
    }
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
    super.onDestroy();
  }
}
