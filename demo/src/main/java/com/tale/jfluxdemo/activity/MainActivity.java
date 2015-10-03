/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tale.jfluxdemo.R;
import com.tale.jfluxdemo.dialog.AddItemDialog;

public class MainActivity extends AppCompatActivity {
  private static final String ADD_ITEM_DIALOG = "AddItemDialog";
  @Bind(R.id.toolbarView) Toolbar toolbarView;
  @Bind(R.id.recyclerView) RecyclerView recyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbarView);
  }

  @OnClick(R.id.addBT) public void showAddDialog() {
    new AddItemDialog().show(getSupportFragmentManager(), ADD_ITEM_DIALOG);
  }
}
