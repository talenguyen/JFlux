package com.tale.jflux;

import java.util.ArrayList;
import java.util.List;

/**
 * Author tale. Created on 7/31/15.
 */
public abstract class Store {

    public static final int STATE_STARTED = 0;

    private List<OnStoreChangeListener> listeners;

    private int state = STATE_STARTED;

    /**
     * Get current state of Store.
     *
     * @return the current state.
     */
    public int getState() {
        return state;
    }

    /**
     * Change current state of Store. This will involve emitChange method to notify for Views
     * which is observing for state of this Store.
     *
     * @param state The new state.
     */
    protected void setState(int state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        emitChange();
    }

    /**
     * Reset state to STATE_STARTED.
     */
    protected void resetState() {
        setState(STATE_STARTED);
    }

    public abstract void onReceivedAction(Action action);

    /**
     * Call to register for change event which emitted by this {@link Store}.
     *
     * @param listener The {@link OnStoreChangeListener}.
     */
    public void registerForChangeEvent(OnStoreChangeListener listener) {
        if (listener == null) {
            return;
        }
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(listener);
    }

    /**
     * Call to unregister for change event. Best practice is called whenever {@link OnStoreChangeListener} not visible. For
     * <b>Android</b> it is onPause().
     */
    public void unregisterForChangeEvent(OnStoreChangeListener listener) {
        if (listener == null || listeners == null || listeners.size() == 0) {
            return;
        }
        listeners.remove(listener);
    }

    /**
     * Notify for {@link OnStoreChangeListener} there is change happened.
     */
    protected void emitChange() {
        if (listeners == null || listeners.size() == 0) {
            return;
        }
        for (OnStoreChangeListener listener : listeners) {
            listener.onStoreChanged();
        }
    }
}
