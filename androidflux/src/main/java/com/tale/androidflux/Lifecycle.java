package com.tale.androidflux;

import android.os.Bundle;

public interface Lifecycle {

  void onSaveInstanceState(Bundle outState);

  void onViewCreated(Bundle savedInstanceState);

  void onResume();

  void onPause();

  void onDestroyView();
}