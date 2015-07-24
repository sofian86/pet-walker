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

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.toString();

    private View view;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_main, container, false);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Button button = (Button)view.findViewById(R.id.login_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"button click called");
                if (v.getId() == R.id.login_btn) {
                    OptionsFragment optionsFragment = OptionsFragment.newInstance("Jaja","z majonezem");
                    fragmentTransaction.replace(R.id.welcome_fragment, optionsFragment);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
