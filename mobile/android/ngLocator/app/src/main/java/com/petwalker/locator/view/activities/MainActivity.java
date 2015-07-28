package com.petwalker.locator.view.activities;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.ngworks.locator.R;
import com.petwalker.locator.view.fragments.OptionsFragment;
import com.petwalker.locator.auth.AuthenticationManager;
import com.petwalker.locator.modules.Injector;
import com.petwalker.locator.view.fragments.LoginActivityFragment;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity implements OptionsFragment.OnOptionsFragmentInteractionListener, LoginActivityFragment.OnLoginFragmentInteractionListener{
    private final static String TAG = MainActivity.class.toString();

    @Inject
    AuthenticationManager authenticationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.inject(this);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.add(R.id.container, new LoginActivityFragment());
        ft.commit();

        setContentView(R.layout.activity_main);
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
    public void onLoginFragmentInteraction(String login) {
        EditText loginView = (EditText)findViewById(R.id.login);
    }
}
