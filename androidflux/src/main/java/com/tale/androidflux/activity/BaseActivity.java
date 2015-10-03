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

public class BaseActivity extends AppCompatActivity {

  private Lifecycle lifecycle;

  public void bindLifecycle(Lifecycle lifecycle) {
    this.lifecycle = lifecycle;
  }

  @Override protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (lifecycle != null) {
      lifecycle.onRestoreInstanceState(savedInstanceState);
    }
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (lifecycle != null) {
      lifecycle.onSaveInstanceState(outState);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    if (lifecycle != null) {
      lifecycle.onResume();
    }
  }

  @Override protected void onPause() {
    if (lifecycle != null) {
      lifecycle.onPause();
    }
    super.onPause();
  }

  @Override protected void onDestroy() {
    if (lifecycle != null) {
      lifecycle.onDestroyView();
    }
    super.onDestroy();
  }
}
