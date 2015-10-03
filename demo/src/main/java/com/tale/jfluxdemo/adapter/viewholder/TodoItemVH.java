/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.tale.jfluxdemo.R;
import com.tale.jfluxdemo.model.Todo;

public class TodoItemVH extends RecyclerView.ViewHolder {

  @Bind(R.id.tvTask) TextView tvTask;

  public TodoItemVH(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(Todo todo) {
    tvTask.setText(todo.task);
  }
}
