/**
 * JFlux
 *
 * Created by Giang Nguyen on 10/3/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jfluxdemo.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.tale.jfluxdemo.ObjectsGraph;
import com.tale.jfluxdemo.R;
import com.tale.jfluxdemo.action.ToDoActionCreators;
import com.tale.jfluxdemo.util.Utils;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class AddItemDialog extends FullscreenDialog {

  @Bind(R.id.etTodo) AppCompatEditText todoET;
  @Bind(R.id.tvTask) AppCompatTextView tvTask;

  private ToDoActionCreators toDoActionCreators;
  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    toDoActionCreators = ObjectsGraph.getDoActionCreators();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_add_item, container, false);
    ButterKnife.bind(this, view);
    setupViews();
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    todoET.requestFocus();
    Utils.showSoftKeyboard(getActivity());
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @OnClick(R.id.btClear) public void clear() {
    todoET.setText(null);
  }

  private void setupViews() {
    // Sync text change to TextView
    Subscription subscription = RxTextView.textChanges(todoET).subscribe(RxTextView.text(tvTask));
    compositeSubscription.add(subscription);
    subscription = RxTextView.editorActions(todoET).subscribe(new Action1<Integer>() {
      @Override public void call(Integer actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          checkAndAddTodo();
        }
      }
    });
    compositeSubscription.add(subscription);
  }

  private void checkAndAddTodo() {
    final String task = todoET.getText().toString();
    if (TextUtils.isEmpty(task)) {
      return;
    }
    // Dispatch create action
    toDoActionCreators.createAddTodoAction(task);
    // Reset text fields.
    todoET.setText(null);
  }
}
