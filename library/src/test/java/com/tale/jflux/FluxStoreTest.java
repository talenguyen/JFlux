package com.tale.jflux;

import org.mockito.Mock;

/**
 * Author tale. Created on 8/1/15.
 */
public class FluxStoreTest {

    @Mock OnChangeListener onChangeListener;
    Action sampleAction;
    FluxStore fluxStore;

    //@Before
    //public void setUp() throws Exception {
    //
    //    MockitoAnnotations.initMocks(this);
    //
    //    fluxStore = new FluxStore() {
    //        @Override public void onReceivedAction(Action payload) {
    //            emitChange();
    //        }
    //    };
    //
    //    sampleAction = new Action() {
    //        @Override
    //        public int getId() {
    //            return 0;
    //        }
    //    };
    //}
    //
    //@org.junit.Test
    //public void testBindView() throws Exception {
    //    fluxStore.registerForChangeEvent(onChangeListener);
    //    fluxStore.onReceivedAction(sampleAction);
    //    Mockito.verify(onChangeListener).onChanged();
    //}
    //
    //@org.junit.Test
    //public void testUnBindView() throws Exception {
    //    // Bind view.
    //    fluxStore.registerForChangeEvent(onChangeListener);
    //    // Then unbind view.
    //    fluxStore.unregisterForChangeEvent(onChangeListener);
    //    fluxStore.onReceivedAction(sampleAction);
    //    // Expect onChanged not be call.
    //    Mockito.verify(onChangeListener, Mockito.never()).onChanged();
    //}

}