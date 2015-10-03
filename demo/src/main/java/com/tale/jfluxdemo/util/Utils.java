/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {
  private Utils() {
  }

  public static void showSoftKeyboard(Context context) {
    InputMethodManager imm =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
  }

  public static void hideSoftKeyboard(Activity activity) {
    final View currentFocus = activity.getCurrentFocus();
    if (currentFocus == null) {
      return;
    }
    InputMethodManager imm =
        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    final IBinder windowToken = currentFocus.getWindowToken();
    imm.hideSoftInputFromWindow(windowToken, 0);
  }
}
