/**
 * UmbalaApp
 *
 * Created by Giang Nguyen on 9/21/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import com.tale.androidflux.fragment.BaseDialogFragment;
import com.tale.jfluxdemo.R;

public class FullscreenDialog extends BaseDialogFragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppFullscreenDialog);
  }

  @Override public void onStart() {
    super.onStart();
    final Dialog dialog = getDialog();
    if (dialog != null) {
      dialog.getWindow()
          .setLayout(WindowManager.LayoutParams.MATCH_PARENT,
              WindowManager.LayoutParams.MATCH_PARENT);
    }
  }

  @Override public void dismiss() {
    super.dismiss();
    final Fragment parentFragment = getParentFragment();
    if (parentFragment instanceof DialogInterface.OnDismissListener) {
      ((DialogInterface.OnDismissListener) parentFragment).onDismiss(null);
    }
  }
}
