/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.adapter;

import com.hannesdorfmann.adapterdelegates.ListDelegationAdapter;
import com.tale.jfluxdemo.adapter.delegate.TodoListDelegateAdapter;
import com.tale.jfluxdemo.model.Todo;
import java.util.List;

public class TodoListAdapter extends ListDelegationAdapter<List<Todo>> {

  public static final int ITEM_TODO = 1;

  public TodoListAdapter() {
    delegatesManager.addDelegate(new TodoListDelegateAdapter(ITEM_TODO));
  }
}
