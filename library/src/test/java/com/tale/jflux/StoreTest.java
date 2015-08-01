package com.tale.jflux;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Author tale. Created on 8/1/15.
 */
public class StoreTest {

    @Mock
    ReactView reactView;
    Action sampleAction;
    Store store;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        store = new Store() {
            @Override
            public void onReceiveAction(Action action) {
                notifyChange();
            }
        };

        sampleAction = new Action() {
            @Override
            public int getId() {
                return 0;
            }
        };
    }

    @org.junit.Test
    public void testBindView() throws Exception {
        store.bindView(reactView);
        store.onReceiveAction(sampleAction);
        Mockito.verify(reactView).onStoreChanged();
    }

    @org.junit.Test
    public void testUnBindView() throws Exception {
        // Bind view.
        store.bindView(reactView);
        // Then unbind view.
        store.unBindView();
        store.onReceiveAction(sampleAction);
        // Expect onStoreChanged not be call.
        Mockito.verify(reactView, Mockito.never()).onStoreChanged();
    }

}