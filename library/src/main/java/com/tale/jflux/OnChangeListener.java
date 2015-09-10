package com.tale.jflux;

/**
 * Author tale. Created on 7/31/15.
 */
public interface OnChangeListener {

    /**
     * Will be called every time changes happened from {@link FluxStore}.
     */
    void onChanged();

}
