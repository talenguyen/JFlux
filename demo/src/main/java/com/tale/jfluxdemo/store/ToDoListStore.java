/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/1/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.store;

import android.util.SparseArray;
import com.tale.jflux.Action;
import com.tale.jflux.Dispatcher;
import com.tale.jflux.FluxStore;
import com.tale.jfluxdemo.action.Actions;
import com.tale.jfluxdemo.model.ToDo;
import java.util.List;

public class TodoListStore extends FluxStore {
  private SparseArray<ToDo> toDoMap;

  /**
   * @param dispatcher {@link Dispatcher} dispatcher
   */
  public TodoListStore(Dispatcher dispatcher) {
    super(dispatcher);
    toDoMap = new SparseArray<>();
  }

  @Override protected void onDispatch(Action action) {
    switch (action.getId()) {
      case Actions.LOAD_TODO_LIST:
        final List<ToDo> toDoList = action.getPayload();
        if (toDoList != null && toDoList.size() > 0) {
          updateMap(toDoList);
        }
        break;
    }
  }

  private void updateMap(List<ToDo> toDoList) {
    toDoMap.clear();
    final int size = toDoList.size();
    for (int i = 0; i < size; i++) {
      toDoMap.put(i, toDoList.get(i));
    }
  }
}
