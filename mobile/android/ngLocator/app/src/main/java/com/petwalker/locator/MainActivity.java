package com.petwalker.locator;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ngworks.locator.R;
import com.petwalker.locator.auth.AuthenticationManager;
import com.petwalker.locator.modules.Injector;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity implements OptionsFragment.OnOptionsFragmentInteractionListener, LoginActivityFragment.OnLoginFragmentInteractionListener{
    private final static String TAG = MainActivity.class.toString();

    @Inject
    AuthenticationManager authenticationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Injector.INSTANCE.inject(this);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Create MainActivity");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsFragmentInteraction(Uri uri) {

    }

    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }
}
