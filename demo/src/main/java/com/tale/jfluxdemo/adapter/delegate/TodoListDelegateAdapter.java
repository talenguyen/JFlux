/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates.AbsAdapterDelegate;
import com.tale.jfluxdemo.R;
import com.tale.jfluxdemo.adapter.viewholder.TodoItemVH;
import com.tale.jfluxdemo.model.Todo;
import java.util.List;

public class TodoListDelegateAdapter extends AbsAdapterDelegate<List<Todo>> {

  public TodoListDelegateAdapter(int viewType) {
    super(viewType);
  }

  @Override public boolean isForViewType(List<Todo> items, int position) {
    return true;
  }

  @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
    final View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
    return new TodoItemVH(view);
  }

  @Override public void onBindViewHolder(@NonNull List<Todo> items, int position,
      @NonNull RecyclerView.ViewHolder holder) {
    ((TodoItemVH) holder).bind(items.get(position));
  }
}
