/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.viewcontroller;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.tale.androidflux.viewcontroller.ReactViewController;
import com.tale.jfluxdemo.adapter.TodoListAdapter;
import com.tale.jfluxdemo.model.Todo;
import com.tale.jfluxdemo.store.TodoListStore;
import java.util.List;

public class TodoListViewController extends ReactViewController {

  private TodoListAdapter todoListAdapter;

  public TodoListViewController(TodoListStore todoListStore) {
    super(todoListStore);
    todoListAdapter = new TodoListAdapter();
  }

  public void takeView(RecyclerView recyclerView) {
    recyclerView.setLayoutManager(
        new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(todoListAdapter);
  }

  @Override public void onChanged() {
    final List<Todo> items = ((TodoListStore) getFluxStore()).getItems();
    todoListAdapter.setItems(items);
  }
}
