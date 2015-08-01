package com.tale.jflux;

/**
 * Author tale. Created on 7/31/15.
 */
public interface Action {

    /**
     * Return the unique id of event which will be used to determine the
     * callback correspond to event.
     *
     * @return The unique id of event in the system.
     */
    int getId();

}
