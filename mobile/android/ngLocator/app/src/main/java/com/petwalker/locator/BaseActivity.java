package com.petwalker.locator;

import android.app.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //((Application)getApplication()).getObjectGraph().inject(this);
    }
}
