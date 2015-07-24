package com.petwalker.locator.modules;

import com.petwalker.locator.auth.AuthenticationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public AuthenticationManager providesAuthenticationManager() {
        return new AuthenticationManager();
    }
}
