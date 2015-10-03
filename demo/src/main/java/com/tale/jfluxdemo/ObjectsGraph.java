/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo;

import android.app.Application;
import com.tale.jflux.Dispatcher;
import com.tale.jfluxdemo.action.ToDoActionCreators;
import com.tale.jfluxdemo.store.TodoListStore;

public class ObjectsGraph {

  private static Dispatcher dispatcher;

  public static void init(Application application) {

  }

  public static Dispatcher getDispatcher() {
    if (dispatcher == null) {
      dispatcher = new Dispatcher();
    }
    return dispatcher;
  }

  public static ToDoActionCreators getDoActionCreators() {
    return new ToDoActionCreators(getDispatcher());
  }

  public static TodoListStore getTodoListStore() {
    return new TodoListStore(getDispatcher());
  }
}
