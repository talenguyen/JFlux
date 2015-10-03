package com.tale.jflux;

import java.util.ArrayList;
import java.util.List;

import static com.tale.jflux.Invariant.invariant;

/**
 * Author tale. Created on 7/31/15.
 */

/**
 * This class should be extended by the stores in your application, like so:
 *
 * var FluxStore = require('FluxStore');
 * var MyDispatcher = require('MyDispatcher');
 *
 * var _foo;
 *
 * class MyStore extends FluxStore {
 *
 * getFoo() {
 * return _foo;
 * }
 *
 * __onDispatch = function(action) {
 * switch(action.type) {
 *
 * case 'an-action':
 * changeState(action.someData);
 * this.__emitChange();
 * break;
 *
 * case 'another-action':
 * changeStateAnotherWay(action.otherData);
 * this.__emitChange();
 * break;
 *
 * default:
 * // no op
 * }
 * }
 *
 * }
 *
 * module.exports = new MyStore(MyDispatcher);
 */
public abstract class FluxStore implements Callback {

  public List<OnChangeListener> listeners;
  // protected, available to subclasses
  protected boolean changed;
  protected String className;
  protected Dispatcher dispatcher;
  // private
  private String dispatchToken;

  /**
   * @param dispatcher {@link Dispatcher} dispatcher
   */
  public FluxStore(Dispatcher dispatcher) {
    className = getClass().getName();

    changed = false;
    this.dispatcher = dispatcher;
    dispatchToken = dispatcher.register(this);
  }

  @Override public void call(Action action) {
    invokeOnDispatch(action);
  }

  /**
   * Add listener.
   *
   * @param listener {@link OnChangeListener} callback
   */
  public void addListener(OnChangeListener listener) {
    if (listeners == null) {
      listeners = new ArrayList<>();
    }
    if (listeners.contains(listener)) {
      return;
    }
    listeners.add(listener);
  }

  /**
   * Remove listener
   *
   * @param listener {@link OnChangeListener} callback
   */
  public void removeListener(OnChangeListener listener) {
    if (listeners == null || listeners.size() == 0) {
      return;
    }
    listeners.remove(listener);
  }

  /**
   * @return {Dispatcher} The dispatcher that this store is registered with.
   */
  public Dispatcher getDispatcher() {
    return this.dispatcher;
  }

  /**
   * @return {string} A string the dispatcher uses to identify each store's
   * registered callback. This is used with the dispatcher's waitFor method
   * to declaratively depend on other stores updating themselves first.
   */
  public String getDispatchToken() {
    return this.dispatchToken;
  }

  /**
   * @return {boolean} Whether the store has changed during the most recent
   * dispatch.
   */
  public boolean hasChanged() {
    invariant(this.dispatcher.isDispatching(),
        "%s.hasChanged(): Must be invoked while dispatching.", this.className);
    return this.changed;
  }

  /**
   * Emit an event notifying listeners that the state of the store has changed.
   */
  protected void emitChange() {
    invariant(this.dispatcher.isDispatching(),
        "%s.__emitChange(): Must be invoked while dispatching.", this.className);
    this.changed = true;
  }

  /**
   * This method encapsulates all logic for invoking __onDispatch. It should
   * be used for things like catching changes and emitting them after the
   * subclass has handled a action.
   *
   * @param action {@link Action} action The data dispatched by the dispatcher, describing
   * something that has happened in the real world: the user clicked, the
   * server responded, time passed, etc.
   */
  protected void invokeOnDispatch(Action action) {
    this.changed = false;
    this.onDispatch(action);
    if (this.changed) {
      notifyChanged();
    }
  }

  /**
   * The callback that will be registered with the dispatcher during
   * instantiation. Subclasses must override this method. This callback is the
   * only way the store receives new data.
   *
   * @param action {@link Action} action The data dispatched by the dispatcher, describing
   * something that has happened in the real world: the user clicked, the
   * server responded, time passed, etc.
   */
  protected abstract void onDispatch(Action action);

  private void notifyChanged() {
    if (listeners != null && listeners.size() > 0) {
      for (OnChangeListener listener : listeners) {
        listener.onChanged();
      }
    }
  }

  public static interface OnChangeListener {
    void onChanged();
  }
}
