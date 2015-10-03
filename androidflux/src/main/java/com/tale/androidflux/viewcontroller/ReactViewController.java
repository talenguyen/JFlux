/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.androidflux.viewcontroller;

import android.os.Bundle;
import com.tale.androidflux.Lifecycle;
import com.tale.jflux.FluxStore;

public abstract class ReactViewController<T extends FluxStore>
    implements Lifecycle, FluxStore.OnChangeListener {
  private T fluxStore;

  public ReactViewController(T fluxStore) {
    this.fluxStore = fluxStore;
  }

  @Override public void onSaveInstanceState(Bundle outState) {

  }

  @Override public void onRestoreInstanceState(Bundle savedInstanceState) {

  }

  @Override public void onResume() {
    fluxStore.addListener(this);
  }

  @Override public void onPause() {
    fluxStore.removeListener(this);
  }

  @Override public void onDestroyView() {

  }

  protected T getFluxStore() {
    return fluxStore;
  }
}
