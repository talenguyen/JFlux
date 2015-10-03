/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.viewcontroller;

import com.tale.androidflux.viewcontroller.ReactViewController;
import com.tale.jfluxdemo.store.TodoListStore;

public class TodoListViewController extends ReactViewController {

  public TodoListViewController(TodoListStore todoListStore) {
    super(todoListStore);
  }

  @Override public void onChanged() {

  }
}
