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

    @Mock FluxStore fluxStore1;
    @Mock FluxStore fluxStore2;

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
        final Callback callback1 = Mockito.mock(Callback.class);
        final Callback callback2 = Mockito.mock(Callback.class);
        dispatcher.register(callback1);
        dispatcher.register(callback2);
        dispatcher.dispatch(action1);
        Mockito.verify(callback1, Mockito.times(1)).call(action1);
        Mockito.verify(callback2, Mockito.times(1)).call(action1);

    }
    //
    //@Test
    //public void testDispatchOne() throws Exception {
    //    dispatcher.registerCallbackForAction(fluxStore1, 1);
    //    dispatcher.registerCallbackForAction(fluxStore2, 2);
    //    dispatcher.dispatch(action1);
    //    Mockito.verify(fluxStore1).onReceivedAction(action1);
    //    Mockito.verify(fluxStore2, Mockito.never()).onReceivedAction(Mockito.any(Action.class));
    //    dispatcher.dispatch(action2);
    //    Mockito.verify(fluxStore2).onReceivedAction(action2);
    //    Mockito.verify(fluxStore1, Mockito.never()).onReceivedAction(action2);
    //
    //}

}