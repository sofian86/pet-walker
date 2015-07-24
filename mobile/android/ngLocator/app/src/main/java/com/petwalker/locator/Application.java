package com.petwalker.locator;

import com.petwalker.locator.modules.ApplicationModule;

import dagger.ObjectGraph;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
public class Application extends android.app.Application {

    private ObjectGraph objectGraph;

    private static Application instance;

    public Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        objectGraph = ObjectGraph.create(new ApplicationModule());
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
