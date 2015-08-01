package com.tale.jflux;

import java.lang.ref.WeakReference;

/**
 * Author tale. Created on 7/31/15.
 */
public abstract class Store {

    private WeakReference<ReactView> reactViewWeakReference;

    public abstract void onReceiveAction(Action action);

    /**
     * Should be called by {@link ReactView} to register for observer changes
     * from this {@link Store}.
     *
     * @param reactView The {@link ReactView}.
     */
    public void bindView(ReactView reactView) {
        reactViewWeakReference = new WeakReference<>(reactView);
    }

    /**
     * Should be call by {@link ReactView} to unregister for changes. Best
     * practice is called whenever {@link ReactView} not visible. For
     * <b>Android</b> it is onPause().
     */
    public void unBindView() {
        reactViewWeakReference.clear();
    }

    /**
     * Notify for {@link ReactView} there is change happened.
     */
    protected void notifyChange() {
        if (reactViewWeakReference != null) {
            final ReactView reactView = reactViewWeakReference.get();
            if (reactView != null) {
                reactView.onStoreChanged();
            }
        }
    }
}
