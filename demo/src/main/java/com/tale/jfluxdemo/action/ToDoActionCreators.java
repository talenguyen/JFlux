/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.action;

import com.tale.jflux.Action;
import com.tale.jflux.Dispatcher;

public class ToDoActionCreators {

  private final Dispatcher dispatcher;

  public ToDoActionCreators(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void createAddTodoAction(String task) {
    dispatcher.dispatch(new Action(Actions.ADD_TODO, task));
  }
}
