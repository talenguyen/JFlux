package com.tale.jfluxdemo;

import android.app.Application;
import android.content.Context;

import com.tale.jflux.Dispatcher;

/**
 * Author tale. Created on 8/1/15.
 */
public class FluxDemoApp extends Application {

    private Dispatcher dispatcher;

    public static FluxDemoApp get(Context context) {
        return ((FluxDemoApp) context.getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dispatcher = new Dispatcher();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
