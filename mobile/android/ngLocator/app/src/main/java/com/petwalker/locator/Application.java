package com.petwalker.locator;

import com.petwalker.locator.modules.ApplicationModule;
import com.petwalker.locator.modules.Injector;
import com.petwalker.locator.modules.RootModule;

import dagger.ObjectGraph;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.init(new RootModule());
    }

}
