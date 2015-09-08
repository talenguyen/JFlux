package com.tale.jflux;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Author tale. Created on 8/1/15.
 */
public class DispatcherTest {

    Dispatcher dispatcher;
    Action action1;
    Action action2;

    @Mock
    Store store1;
    @Mock
    Store store2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dispatcher = new Dispatcher();
        action1 = new Action() {
            @Override
            public int getId() {
                return 1;
            }
        };
        action2 = new Action() {
            @Override
            public int getId() {
                return 2;
            }
        };
    }

    @Test
    public void testDispatchMany() throws Exception {
        dispatcher.registerCallbackForAction(store1, 1);
        dispatcher.registerCallbackForAction(store2, 1);
        dispatcher.dispatch(action1);
        Mockito.verify(store1).onReceivedAction(action1);
        Mockito.verify(store2).onReceivedAction(action1);

    }

    @Test
    public void testDispatchOne() throws Exception {
        dispatcher.registerCallbackForAction(store1, 1);
        dispatcher.registerCallbackForAction(store2, 2);
        dispatcher.dispatch(action1);
        Mockito.verify(store1).onReceivedAction(action1);
        Mockito.verify(store2, Mockito.never()).onReceivedAction(Mockito.any(Action.class));
        dispatcher.dispatch(action2);
        Mockito.verify(store2).onReceivedAction(action2);
        Mockito.verify(store1, Mockito.never()).onReceivedAction(action2);

    }

}