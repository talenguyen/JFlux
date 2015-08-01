package com.tale.jflux;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Author tale. Created on 7/31/15.
 */
public class Dispatcher {

    private Hashtable<Integer, List<Store>> actionStoresMap;

    public Dispatcher() {
        actionStoresMap = new Hashtable<>();
    }

    /**
     * Dispatch the {@link Action}
     *
     * @param action the action to be dispatch.
     */
    public void dispatch(Action action) {
        final int type = action.getId();
        final List<Store> stores = actionStoresMap.get(type);
        if (stores == null || stores.size() == 0) {
            return;
        }

        for (Store store : stores) {
            store.onReceiveAction(action);
        }
    }

    /**
     * Register callback for the action. The callback will be involved if
     * correspond action is dispatched.
     *
     * @param store  the callback.
     * @param action the action which the callback observer.
     */
    public void registerCallbackForAction(Store store, int action) {
        List<Store> stores = this.actionStoresMap.get(action);
        if (stores == null) {
            stores = new ArrayList<>();
            this.actionStoresMap.put(action, stores);
        }

        if (!stores.contains(store)) {
            stores.add(store);
        }
    }

    /**
     * Unregister callback for the action. The callback will be removed.
     *
     * @param store  the callback.
     * @param action the action.
     */
    public void unregisterCallbackForAction(Store store, int action) {
        final List<Store> stores = actionStoresMap.get(action);
        if (stores != null && stores.contains(store)) {
            stores.remove(store);
        }
    }
}
