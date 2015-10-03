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
import com.tale.jfluxdemo.model.Todo;
import java.util.ArrayList;
import java.util.List;

public class TodoListStore extends FluxStore {
  private SparseArray<Todo> toDoMap;

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
        final List<Todo> todoList = action.getPayload();
        if (todoList != null && todoList.size() > 0) {
          updateMap(todoList);
        }
        // Don't forget to call emit change.
        emitChange();
        break;
      case Actions.ADD_TODO:
        final int id = toDoMap.size();
        final String task = action.getPayload();
        toDoMap.put(id, createTodoItem(task));
        // Don't forget to call emit change.
        emitChange();
        break;
    }
  }

  public List<Todo> getItems() {
    final int size = toDoMap.size();
    if (size > 0) {
      final List<Todo> items = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
        final Todo todo = toDoMap.get(i);
        items.add(todo);
      }
      return items;
    }
    return null;
  }

  private Todo createTodoItem(String task) {
    final Todo todo = new Todo();
    todo.task = task;
    return todo;
  }

  private void updateMap(List<Todo> todoList) {
    toDoMap.clear();
    final int size = todoList.size();
    for (int i = 0; i < size; i++) {
      toDoMap.put(i, todoList.get(i));
    }
  }
}
