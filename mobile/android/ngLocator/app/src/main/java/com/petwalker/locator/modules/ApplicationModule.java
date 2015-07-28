package com.petwalker.locator.modules;

import com.petwalker.locator.view.activities.MainActivity;
import com.petwalker.locator.auth.AuthenticationManager;
import com.petwalker.locator.view.fragments.LoginActivityFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
@Module(complete = false,library=true, injects = {MainActivity.class, LoginActivityFragment.class})
public class ApplicationModule {

    @Provides
    @Singleton
    public AuthenticationManager providesAuthenticationManager() {
        return  AuthenticationManager.getInstance();
    }


}
