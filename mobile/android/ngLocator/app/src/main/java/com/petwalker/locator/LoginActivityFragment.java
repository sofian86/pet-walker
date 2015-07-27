package com.petwalker.locator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ngworks.locator.R;

public class LoginActivityFragment extends Fragment {

    private static final String TAG = LoginActivityFragment.class.toString();

    private View view;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private boolean active;

    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        active = true;

        view = inflater.inflate(R.layout.fragment_login, container, false);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Button button = (Button)view.findViewById(R.id.login_btn);
        final LoginActivityFragment laf = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"button click called");
                if (v.getId() == R.id.login_btn) {
                    OptionsFragment optionsFragment = OptionsFragment.newInstance("Jaja","z majonezem");
                    fragmentTransaction.replace(R.id.welcome_fragment, optionsFragment);
                    fragmentTransaction.addToBackStack("login");
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }
    public interface OnLoginFragmentInteractionListener {
        void onMainFragmentInteraction(Uri uri);
    }

}
